package com.itchat.utils;

import com.itchat.netty.NettyServerNode;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    /**
     * 增加在线人数
     *
     * @param serverNode
     */
    public static void incrementOnlineCounts(NettyServerNode serverNode) {
        dealOnlineCounts(serverNode, 1);
    }

    /**
     * 减少在线人数
     *
     * @param serverNode
     */
    public static void decrementOnlineCounts(NettyServerNode serverNode) {
        dealOnlineCounts(serverNode, -1);
    }

    /**
     * 处理在线人数的增减
     *
     * @param serverNode
     * @param counts
     */
    public static void dealOnlineCounts(NettyServerNode serverNode,
                                        Integer counts) {
        String path = "/server-list";
        CuratorFramework zkClient = CuratorConfig.getClient();

        try {
            List<String> nodeList = zkClient.getChildren().forPath(path);
            for (String node : nodeList) {
                String nodePath = path + "/" + node;
                String nodeJson = new String(zkClient.getData().forPath(nodePath));

                NettyServerNode zookeeperNettyServer = JsonUtils.jsonToPojo(nodeJson, NettyServerNode.class);

                if (zookeeperNettyServer.getIp().equals(serverNode.getIp())
                        &&
                        (zookeeperNettyServer.getPort().intValue() == serverNode.getPort().intValue())) {
                    zookeeperNettyServer.setOnlineCounts(zookeeperNettyServer.getOnlineCounts() + counts);

                    // 更新zookeeper中节点数据
                    zkClient.setData().forPath(nodePath, JsonUtils.objectToJson(zookeeperNettyServer).getBytes(StandardCharsets.UTF_8));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

