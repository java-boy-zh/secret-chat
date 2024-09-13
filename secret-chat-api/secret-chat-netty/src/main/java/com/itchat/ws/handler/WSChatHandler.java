package com.itchat.ws.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 王哲
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

        // 获取channel
        Channel currentChannel = context.channel();
        // 拿到channelId
        String currentChannelLongId = currentChannel.id().asLongText();
        String currentChannelShortId = currentChannel.id().asShortText();
        log.info("客户端当前Channel的长ID->{},短ID->{}", currentChannelLongId, currentChannelShortId);

        // 构建传输载体
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(currentChannelLongId);

        // 传输数据
        currentChannel.writeAndFlush(textWebSocketFrame);
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

        // 将channel的长id从 ChannelGroup 进行移除
        clients.remove(channel);
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
        // 将channel的长id从 ChannelGroup 进行移除
        clients.remove(channel);
    }
}
