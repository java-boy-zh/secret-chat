package com.itchat.mq.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName RabbitMQConfig.java
 * @create 2024年09月14日 下午2:50
 * @Description RabbitMQ的配置类
 * @Version V1.0
 */
@Configuration
public class RabbitMQConfig {

    // 定义交换机
    public static final String EXCHANGE = "exchange";

    // 定义队列
    public static final String QUEUE = "queue";

    // 定义路由键
    public static final String ROUTING_KEY = "imchat.test";

    // 发送信息到消息队列 并保存至数据库的 路由KEY
    public static final String ROUTING_KEY_MESSAGE_SAVE = "imchat.message.save";

    // 创建交换机
    @Bean(EXCHANGE)
    public Exchange exchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE)    // 使用Topic模式
                .durable(true)              // 持久化
                .build();
    }

    // 创建队列
    @Bean(QUEUE)
    public Queue queue() {
        return QueueBuilder
                .durable(QUEUE)
                .build();
    }

    // 将交换机和队列进行绑定
    @Bean
    public Binding binding(@Qualifier(EXCHANGE) Exchange exchange,
                           @Qualifier(QUEUE) Queue queue) {

        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("imchat.#")
                .noargs();
    }

}
