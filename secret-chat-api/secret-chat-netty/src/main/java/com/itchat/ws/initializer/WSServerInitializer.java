package com.itchat.ws.initializer;

import com.itchat.ws.handler.HeartBeatHandler;
import com.itchat.ws.handler.WSChatHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName WSServerInitializer.java
 * @create 2024年09月13日 下午2:45
 * @Description WebSocket初始化器，channel注册后，会执行里面对应的初始化方法
 * @Version V1.0
 */
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 通过 SocketChannel 获取对应的管道
        ChannelPipeline pipeline = channel.pipeline();
        /**
         * 对管道添加处理器
         */
        /*=============================支持HTTP相关协议的Handler=======================================*/

        // 添加编解码器 当请求到服务端需要解码，到客户端进行编码
        // WebSocket 基于HTTP，所以需要保留http的编解码器
        pipeline.addLast(new HttpServerCodec());
        // 添加对大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 对HttpMessage进行聚合，聚合成为 FullHttpResponse/FullHttpRequest
        // 在Netty的编程中 基本上都会用到该Handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        /*=============================支持HTTP相关协议的Handler=======================================*/

        /*=============================支持心跳控制的Handler=======================================*/

        // 针对客户端，如果在30分钟没有向服务端发送读写心跳(ALL)，则主动断开连接
        // 如果是读空闲或者写空间，不做任何处理
        pipeline.addLast(new IdleStateHandler(10, 25, 30 * 60));
        pipeline.addLast(new HeartBeatHandler());

        /*=============================持心跳控制的Handler=======================================*/

        /*=============================支持WebSocket相关协议的Handler=======================================*/

        /**
         * WebSocket处理的协议，用于指定客户端链接时访问的路径
         * 处理： 链接，关闭，重试，维持心跳等
         * 对于WebSocket来说，数据都是以frames进行传输的，不同的数据类型所对应的frames也都不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        /*=============================支持WebSocket相关协议的Handler=======================================*/

        // 添加自定义的处理器
        pipeline.addLast(new WSChatHandler());
    }

}
