package com.itchat.ws.session;

import io.netty.channel.Channel;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName UserChannelSession.java
 * @create 2024年09月13日 下午7:48
 * @Description 用户会话管理 用户userId和channel关联
 * @Version V1.0
 */
public class UserChannelSession {

    // 用于多端同时接收消息 运行同一账号在多个设备登录
    // key: userId, value: 多个用户的channel
    private static Map<String, List<Channel>> multiSession = new HashMap<>();

    // 用于记录用户id和客户端channel长id的关联关系
    private static Map<String, String> userChannelIdRelation = new HashMap<>();

    public static void putUserChannelIdRelation(String channelId, String userId) {
        userChannelIdRelation.put(channelId, userId);
    }

    public static String getUserIdByChannelId(String channelId) {
        return userChannelIdRelation.get(channelId);
    }

    public static void putMultiChannels(String userId, Channel channel) {
        List<Channel> multiChannels = getMultiChannels(userId);
        if (CollectionUtils.isEmpty(multiChannels)) {
            multiChannels = new ArrayList<>();
        }
        multiChannels.add(channel);
    }

    public static List<Channel> getMultiChannels(String userId) {
        return multiSession.get(userId);
    }

    public static void outputMulti() {

        System.out.println("++++++++++++++++++");

        for (Map.Entry<String, List<Channel>> entry : multiSession.entrySet()) {
            System.out.println("----------");

            System.out.println("UserId: " + entry.getKey());
            List<Channel> temp = entry.getValue();
            for (Channel c : temp) {
                System.out.println("\t\t ChannelId: " + c.id().asLongText());
            }

            System.out.println("----------");
        }
        
        System.out.println("++++++++++++++++++");
    }
}
