package com.itchat.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName HttpServerInitializer.java
 * @create 2024年09月13日 下午2:45
 * @Description HttpServer初始化器，channel注册后，会执行里面对应的初始化方法
 * @Version V1.0
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 通过 SocketChannel 获取对应的管道
        ChannelPipeline pipeline = channel.pipeline();
        /**
         * 对管道添加处理器
         */
        // 添加编解码器 当请求到服务端需要解码，到客户端进行编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        // 添加自定义的处理器，当请求返回时带上 “hello 王青玄~”
        pipeline.addLast("", null);
    }

}
