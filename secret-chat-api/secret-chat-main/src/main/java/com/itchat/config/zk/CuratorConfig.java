package com.itchat.config.zk;

import com.itchat.common.BaseInfoProperties;
import com.itchat.netty.NettyServerNode;
import com.itchat.utils.JsonUtils;
import com.itchat.utils.RedisOperator;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConfigurationProperties(prefix = "zookeeper.curator")
@Data
public class CuratorConfig extends BaseInfoProperties {

    private String host;                    // 单机/集群的ip:port地址
    private Integer connectionTimeoutMs;    // 连接超时时间
    private Integer sessionTimeoutMs;         // 会话超时时间
    private Integer sleepMsBetweenRetry;    // 每次重试的间隔时间
    private Integer maxRetries;             // 最大重试次数
    private String namespace;               // 命名空间（root根节点名称）

    public static final String path = "/server-list";

    @Resource
    private RedisOperator redisOperator;

    @Bean("curatorClient")
    public CuratorFramework curatorClient() {
        // 三秒后重连一次，只连一次
        //RetryPolicy retryOneTime = new RetryOneTime(3000);
        // 每3秒重连一次，重连3次
        //RetryPolicy retryNTimes = new RetryNTimes(3, 3000);
        // 每3秒重连一次，总等待时间超过10秒则停止重连
        //RetryPolicy retryPolicy = new RetryUntilElapsed(10 * 1000, 3000);
        // 随着重试次数的增加，重试的间隔时间也会增加（推荐）
        RetryPolicy backoffRetry =
                new ExponentialBackoffRetry(sleepMsBetweenRetry, maxRetries);

        // 声明初始化客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(host)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .retryPolicy(backoffRetry)
                .namespace(namespace)
                .build();
        client.start();     // 启动curator客户端

//         try {
//             // 递归创建目录 并设置值
//            client.create().creatingParentContainersIfNeeded().forPath("/abc", "123".getBytes());
//         } catch (Exception e) {
//            e.printStackTrace();
//         }

        addWatch(path, client);

        return client;
    }

    /**
     * 注册节点的事件监听
     */
    public void addWatch(String path, CuratorFramework client) {
        CuratorCache curatorCache = CuratorCache.build(client, path);
        curatorCache
                .listenable()
                .addListener((type, oldData, data) -> {
                    /**
                     * type : 当前监听到的事件类型
                     * oldData : 节点更新前的数据和状态(不一定存在)
                     * data : 节点更新后的数据和状态(不一定存在)
                     */
                    log.info("当前监听到的事件类型->{}", type);
//                    log.info("节点更新前的数据和状态(不一定存在)->{}",oldData);
//                    log.info("节点更新后的数据和状态(不一定存在)->{}",data);

                    //NODE_CREATED : 节点创建事件
                    //NODE_CHANGED : 节点更新事件
                    //NODE_DELETED : 节点删除事件

                    switch (type) {
                        case NODE_CHANGED -> log.info("触发节点更新事件");
                        case NODE_CREATED -> log.info("触发节点创建事件");
                        case NODE_DELETED -> {
                            log.info("触发节点删除事件");

                            // 节点被删除后 移除Redis中存储的端口号
                            String oldDataJson = new String(oldData.getData());
                            if (StringUtils.isNotBlank(oldDataJson)) {
                                NettyServerNode oldServer =
                                        JsonUtils.jsonToPojo(oldDataJson, NettyServerNode.class);
                                log.info("节点更新前的数据和状态(不一定存在),路径->{}", oldData.getPath());

                                String oldPort = oldServer.getPort() + "";
                                String portKey = "netty_port";

                                redisOperator.hdel(portKey, oldPort);
                            }

                        }
                        default -> {
                            log.info("没有触发任何节点事件");
                            break;
                        }
                    }
                });
        // 开启监听
        curatorCache.start();
    }

}
