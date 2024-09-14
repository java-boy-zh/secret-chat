package com.itchat.mq.rabbitmq;

import com.itchat.mq.rabbitmq.config.RabbitMQConfig;
import com.itchat.netty.ChatMsg;
import com.itchat.service.ChatMessageService;
import com.itchat.utils.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName RabbitMQConsumer.java
 * @create 2024年09月14日 下午4:40
 * @Description RabbitMQ的消费者
 * @Version V1.0
 */
@Component
@Slf4j
public class RabbitMQConsumer {

    @Resource
    private ChatMessageService chatMessageService;

    @RabbitListener(queues = {RabbitMQConfig.QUEUE})
    public void watchQueue(String payload, Message message) {

        String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
        log.info("RabbitMQ的消费者,receivedRoutingKey = {}", receivedRoutingKey);

        if (receivedRoutingKey.equals(RabbitMQConfig.ROUTING_KEY_MESSAGE_SAVE)) {
            String msg = payload;
            ChatMsg chatMsg = JsonUtils.jsonToPojo(msg, ChatMsg.class);

            log.info("RabbitMQ的消费者,chatMsg = {}", chatMsg.toString());

            chatMessageService.saveMessage(chatMsg);
        }
    }

}
