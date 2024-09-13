package com.itchat.http.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName HttpHandler.java
 * @create 2024年09月13日 下午2:57
 * @Description 自定义的管道处理器
 * @Version V1.0
 */
@Slf4j
public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext context, HttpObject http) throws Exception {
        // 获取channel
        Channel channel = context.channel();

        // 如果HttpObject 属于是Http客户端的请求 再进行业务处理
        if (http instanceof HttpRequest) {
            // 打印客户端的远程地址
            log.info("客户端远程地址为{}", channel.remoteAddress());

            // 通过缓冲区定义发送的消息，读写数据都是通过缓冲区进行数据交换的
            ByteBuf content = Unpooled.copiedBuffer("hello 王青玄~", CharsetUtil.UTF_8);

            // 构建Http的响应
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    content);
            // 为Http客户端的响应添加数据类型和数据长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 将响应数据写入缓冲区刷新到客户端
            context.writeAndFlush(response);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel...注册");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel...移除");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel...活跃");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel...不活跃");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel...读取数据完毕");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("用户事件触发");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel可写更改");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("捕获到异常");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler...添加");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler...移除");
        super.handlerRemoved(ctx);
    }
}
