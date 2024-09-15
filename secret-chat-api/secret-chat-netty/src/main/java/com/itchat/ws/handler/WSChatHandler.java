package com.itchat.ws.handler;

import com.a3test.component.idworker.IdWorkerConfigBean;
import com.a3test.component.idworker.Snowflake;
import com.itchat.enums.MessageSendEnum;
import com.itchat.enums.MsgTypeEnum;
import com.itchat.mq.rabbitmq.MessagePublisher;
import com.itchat.netty.ChatMsg;
import com.itchat.netty.DataContent;
import com.itchat.netty.NettyServerNode;
import com.itchat.result.GraceJSONResult;
import com.itchat.utils.*;
import com.itchat.ws.session.UserChannelSession;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName WSChatHandler.java
 * @create 2024年09月13日 下午2:57
 * @Description 自定义的管道处理器
 * TextWebSocketFrame:
 * 用于为websocket专门处理的文本数据对象，Frame是数据(消息)的载体
 * @Version V1.0
 */
@Slf4j
public class WSChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有的channel
    private static ChannelGroup clients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext context,
                                TextWebSocketFrame frame) throws Exception {
        // 获得客户端传递过来的消息
        String content = frame.text();
        log.info("客户端传递过来的消息为->{}", content);

        // 将消息转成接收类
        DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
        ChatMsg chatMsg = dataContent.getChatMsg();
        // 根据消息 获取其中的一些数据
        String msgText = chatMsg.getMsg();
        String receiverId = chatMsg.getReceiverId();
        String senderId = chatMsg.getSenderId();
        // 时间校准，以服务器的时间为准
        chatMsg.setChatTime(LocalDateTime.now());
        Integer msgType = chatMsg.getMsgType();

        // 获取channel
        Channel currentChannel = context.channel();
        // 拿到channelId
        String currentChannelLongId = currentChannel.id().asLongText();
        String currentChannelShortId = currentChannel.id().asShortText();

        // 发送消息前 需要进行黑名单判断
        GraceJSONResult result = OkHttpUtil
                .get("http://127.0.0.1:1000/main/friendship/isBlack?friendId1st=" + receiverId
                        + "&friendId2nd=" + senderId);
        boolean isBlack = (boolean) result.getData();
        if (isBlack) {
            return;
        }

        // 判断消息类型 根据不同的类型 做不同的处理
        MsgTypeEnum msgTypeEnum = MsgTypeEnum.getByType(msgType);
        assert msgTypeEnum != null;
        switch (msgTypeEnum) {
            // 当websocket初次open的时候，初始化channel，把channel和用户userid关联起来
            case CONNECT_INIT -> {
                UserChannelSession.putMultiChannels(senderId, currentChannel);
                UserChannelSession.putUserChannelIdRelation(senderId, currentChannelLongId);

                NettyServerNode minNode = dataContent.getServerNode();
                // 初次连接后，该节点下的在线人数累加
                ZookeeperRegister.incrementOnlineCounts(minNode);

                // 获得ip+端口，在redis中设置关系，以便在前端设备断线后减少在线人数
                Jedis jedis = JedisPoolUtils.getJedis();
                jedis.set(senderId, JsonUtils.objectToJson(minNode));
            }

            // 文字表情消息, 图片消息, 视频消息, 音频消息
            case WORDS, IMAGE, VIDEO, VOICE -> {
                // 此处为mq异步解耦，保存信息到数据库，数据库无法获得信息的主键id，
                // 所以此处可以用snowflake直接生成唯一的主键id
                Snowflake snowflake = new Snowflake(new IdWorkerConfigBean());
                String sid = snowflake.nextId();

                chatMsg.setMsgId(sid);

                // 获取所有接收者的Channel集合
                List<Channel> receiverMultiChannels = UserChannelSession.getMultiChannels(receiverId);
                if (CollectionUtils.isEmpty(receiverMultiChannels)) {
                    // receiverMultiChannels为空，表示用户离线/断线状态，
                    // 消息不需要发送，后续可以存储到数据库
                    chatMsg.setIsReceiverOnLine(false);

                } else {
                    chatMsg.setIsReceiverOnLine(true);

                    sendDataContentByChannel(dataContent, chatMsg, receiverMultiChannels, MessageSendEnum.SEND_TO_FRIEND);
                }

                // 将消息通过MQ的方式 异步 存储到数据库
                MessagePublisher.sendMsgToSave(chatMsg);
            }
        }
        // 1 对自己发送 2 对朋友发送
        // 将消息同步给自己的其他端设备
        List<Channel> myOtherChannels = UserChannelSession.getMyOtherChannels(senderId, currentChannelLongId);
        sendDataContentByChannel(dataContent, chatMsg, myOtherChannels, MessageSendEnum.SEND_TO_ME);

        UserChannelSession.outputMulti();
    }

    private void sendDataContentByChannel(DataContent dataContent,
                                          ChatMsg chatMsg,
                                          List<Channel> receiverMultiChannels,
                                          MessageSendEnum messageSendEnum) {
        for (Channel receiverMultiChannel : receiverMultiChannels) {
            ChannelId receiverChannelId = receiverMultiChannel.id();
            Channel channel = clients.find(receiverChannelId);
            if (channel != null) {
                // 先判断是否属于语音发送 需要给前端是否已读标志
                Integer msgType = chatMsg.getMsgType();
                if (msgType == MsgTypeEnum.VOICE.type
                        &&
                        messageSendEnum.type == MessageSendEnum.SEND_TO_FRIEND.type) {
                    chatMsg.setIsRead(false);
                }

                // 组装消息 发送出去
                dataContent.setChatMsg(chatMsg);

                String chatTime = LocalDateUtils.format(chatMsg.getChatTime(), LocalDateUtils.DATETIME_PATTERN_2);
                dataContent.setChatTime(chatTime);

                channel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(dataContent)));
            }
        }
    }

    /**
     * 客户端链接到服务器之后 需要存储channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 获取到channel的长id
        Channel channel = ctx.channel();
        String currentChannelLongId = channel.id().asLongText();
        log.info("客户端链接成功，当前Channel的长ID->{}", currentChannelLongId);

        // 将channel的长id放入 ChannelGroup 进行管理
        clients.add(channel);
    }

    /**
     * 客户端断开链接到服务器之后 需要移除channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 获取到channel的长id
        Channel channel = ctx.channel();
        String currentChannelLongId = channel.id().asLongText();
        log.info("客户端移除链接，当前Channel的长ID->{}", currentChannelLongId);

        // 移除多余的会话
        String userId = UserChannelSession.getUserIdByChannelId(currentChannelLongId);
        UserChannelSession.removeUselessChannels(userId, currentChannelLongId);

        // 将channel的长id从 ChannelGroup 进行移除
        clients.remove(channel);

        // 离线后需要将zookeeper用户在线人数减一
        Jedis jedis = JedisPoolUtils.getJedis();
        NettyServerNode nettyServerNode = JsonUtils.jsonToPojo(jedis.get(userId), NettyServerNode.class);
        ZookeeperRegister.decrementOnlineCounts(nettyServerNode);

        jedis.del(userId);
    }

    /**
     * 客户端链接服务端异常被捕获 需要移除channel
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 获取到channel的长id
        Channel channel = ctx.channel();
        String currentChannelLongId = channel.id().asLongText();
        log.info("客户端链接发生异常，移除链接，当前Channel的长ID->{}", currentChannelLongId);

        // 发生异常之后 关闭链接
        channel.close();

        // 移除多余的会话
        String userId = UserChannelSession.getUserIdByChannelId(currentChannelLongId);
        UserChannelSession.removeUselessChannels(userId, currentChannelLongId);

        // 将channel的长id从 ChannelGroup 进行移除
        clients.remove(channel);

        // 离线后需要将zookeeper用户在线人数减一
        Jedis jedis = JedisPoolUtils.getJedis();
        NettyServerNode nettyServerNode = JsonUtils.jsonToPojo(jedis.get(userId), NettyServerNode.class);
        ZookeeperRegister.decrementOnlineCounts(nettyServerNode);

        jedis.del(userId);
    }
}
