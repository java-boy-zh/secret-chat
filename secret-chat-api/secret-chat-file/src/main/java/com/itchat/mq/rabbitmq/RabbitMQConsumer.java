package com.itchat.mq.rabbitmq;

import com.itchat.mq.rabbitmq.config.RabbitMQConfig;
import com.itchat.netty.ChatMsg;
import com.itchat.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName RabbitMQConsumer.java
 * @create 2024年09月14日 下午3:11
 * @Description RabbitMQ的消费者
 * @Version V1.0
 */
@Component
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = {RabbitMQConfig.QUEUE})
    public void watchQueue(String payload, Message message) {
//        log.info("RabbitMQ的消费者,payload = {}", payload);

        String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
        log.info("RabbitMQ的消费者,receivedRoutingKey = {}", receivedRoutingKey);

        if (receivedRoutingKey.equals(RabbitMQConfig.ROUTING_KEY)) {
            String msg = payload;
            ChatMsg chatMsg = JsonUtils.jsonToPojo(msg, ChatMsg.class);
            log.info("RabbitMQ的消费者,chatMsg = {}", chatMsg.toString());
        }
    }

}
