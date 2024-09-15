package com.itchat.utils;

import com.itchat.netty.NettyServerNode;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Auther 王青玄
 */
public class ZookeeperRegister {

    public static void registerNettyServer(String nodeName,
                                           String ip,
                                           Integer port) {
        CuratorFramework zkClient = CuratorConfig.getClient();
        String path = "/" + nodeName;
        Stat stat = null;
        try {
            stat = zkClient.checkExists().forPath(path);

            if (stat == null) {
                zkClient.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(path);
            }

            // 创建对应的临时节点，值可以放在线人数，默认为初始化的0
            NettyServerNode serverNode = new NettyServerNode();
            serverNode.setIp(ip);
            serverNode.setPort(port);
            String nodeJson = JsonUtils.objectToJson(serverNode);

            zkClient.create()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(path + "/im-", nodeJson.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }
}

