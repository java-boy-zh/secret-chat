package com.itchat;

import com.itchat.utils.JedisPoolUtils;
import com.itchat.utils.ZookeeperRegister;
import com.itchat.ws.initializer.WSServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName NettyApplication.java
 * @create 2024年09月13日 下午2:24
 * @Description 聊天服务启动类
 * @Version V1.0
 */
@Slf4j
public class NettyApplication {

    public static final Integer nettyDefaultPort = 875;
    public static final String initOnlineCounts = "0";

    public static void main(String[] args) {
        // 0) 获取服务启动端口号
        Integer nettyPort = selectPort(nettyDefaultPort);

        // 0.1) 注册当前Netty服务到Zookeeper
        ZookeeperRegister.registerNettyServer(
                "server-list",
                ZookeeperRegister.getLocalIp(),
                nettyPort
        );

        // 1) 创建主从线程组
        // 1.创建主线程组，负责接收客户端链接但不负责处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 2.创建从线程组，负责处理主线程组抛过来的任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 2) 创建Netty服务器
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)                // 对服务器设置主从线程组
                    .channel(NioServerSocketChannel.class)      // 设置NIO的双向信道
                    .childHandler(new WSServerInitializer());   // 设置信道的处理器，用于处理从线程组的任务

            // 3) 设置Netty服务端口号，并设置为同步启动
            ChannelFuture channelFuture = server.bind(nettyPort).sync();

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

    public static Integer selectPort(Integer port) {
        String portKey = "netty_port";
        Jedis jedis = JedisPoolUtils.getJedis();

        Map<String, String> portMap = jedis.hgetAll(portKey);

        // 由于map中的key都应该是整数类型的port，所以先转换成整数后，再比对，否则string类型的比对会有问题
        List<Integer> portList = portMap.entrySet().stream()
                .map(entry -> Integer.valueOf(entry.getKey()))
                .collect(Collectors.toList());

        Integer nettyPort = null;
        if (portList == null || portList.isEmpty()) {
            // step2: 编码到此处先运行测试看一下结果
            jedis.hset(portKey, port + "", initOnlineCounts);
            nettyPort = port;
        } else {
            // 循环portList，获得最大值，并且累加10
            Optional<Integer> maxInteger = portList.stream().max(Integer::compareTo);
            Integer maxPort = maxInteger.get().intValue();
            Integer currentPort = maxPort + 10;
            jedis.hset(portKey, currentPort + "", initOnlineCounts);
            nettyPort = currentPort;
        }
        // step3: 编码到此处先运行测试看一下最终结果
        return nettyPort;
    }

}
