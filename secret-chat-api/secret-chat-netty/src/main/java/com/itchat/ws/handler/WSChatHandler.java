package com.itchat.ws.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
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
}
