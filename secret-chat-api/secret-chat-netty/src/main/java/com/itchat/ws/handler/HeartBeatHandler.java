package com.itchat.ws.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName HeartBeatHandler.java
 * @create 2024年09月14日 上午8:38
 * @Description 心跳链接处理器
 * @Version V1.0
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断evt是否是IdleStateEvent(空闲事件状态，包含 读空闲/写空闲/读写空闲)
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            IdleState state = stateEvent.state();

            switch (state) {
                case READER_IDLE -> log.info("进入读空闲...");
                case WRITER_IDLE -> log.info("进入写空闲...");
                case ALL_IDLE -> {
                    log.info("进入读写空闲...");
                    
                    // 释放掉处于读写空闲的channel
                    Channel channel = ctx.channel();
                    // 关闭无用的channel，以防资源浪费
                    channel.close();
                }
            }
        }
    }
}
