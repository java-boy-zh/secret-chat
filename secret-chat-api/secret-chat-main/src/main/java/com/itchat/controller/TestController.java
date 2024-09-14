package com.itchat.controller;

import com.itchat.mq.rabbitmq.config.RabbitMQConfig;
import com.itchat.netty.ChatMsg;
import com.itchat.utils.JsonUtils;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName TestController.java
 * @create 2024年09月04日 下午12:57
 * @Description 测试控制器
 * @Version V1.0
 */
@RestController
@RequestMapping("/index")
public class TestController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public String index() {
        return "main-index";
    }

    @GetMapping("/mq")
    public String mq() {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setMsg("hello world~~~");
        String msg = JsonUtils.objectToJson(chatMsg);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                "imchat.test",
                msg
        );
        return "mq";
    }

}
