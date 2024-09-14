package com.itchat.mq.rabbitmq;

import com.itchat.netty.ChatMsg;
import com.itchat.utils.JsonUtils;

public class MessagePublisher {

    // 定义交换机
    public static final String EXCHANGE = "exchange";

    // 定义队列
    public static final String QUEUE = "queue";

    // 发送信息到消息队列 并保存至数据库的 路由KEY
    public static final String ROUTING_KEY_MESSAGE_SAVE = "imchat.message.save";

    public static void sendMsgToSave(ChatMsg msg) throws Exception {
        RabbitMQConnectUtils connectUtils = new RabbitMQConnectUtils();
        connectUtils.sendMsg(JsonUtils.objectToJson(msg),
                EXCHANGE,
                ROUTING_KEY_MESSAGE_SAVE);
    }
}
