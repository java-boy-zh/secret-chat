package com.itchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName NettyApplication.java
 * @create 2024年09月13日 下午2:24
 * @Description 聊天服务启动类
 * @Version V1.0
 */
@Slf4j
public class NettyApplication {

    public static void main(String[] args) {
        // 1) 创建主从线程组
        // 1.创建主线程组，负责接收客户端链接但不负责处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 2.创建从线程组，负责处理主线程组抛过来的任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 2) 创建Netty服务器
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup,workerGroup)             // 对服务器设置主从线程组
                    .channel(NioServerSocketChannel.class)  // 设置NIO的双向信道
                    .childHandler(null);                    // 设置信道的处理器，用于处理从线程组的任务
            // 3) 设置Netty服务端口号，并设置为同步启动
            ChannelFuture channelFuture = server.bind(875).sync();
            // 4) 监听关闭的channel
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 5) 优雅地关闭服务
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
