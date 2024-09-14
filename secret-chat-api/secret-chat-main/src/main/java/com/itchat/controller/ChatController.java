package com.itchat.controller;

import com.itchat.common.BaseInfoProperties;
import com.itchat.result.GraceJSONResult;
import com.itchat.service.ChatMessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName ChatController.java
 * @create 2024年09月14日 下午5:15
 * @Description 消息控制器
 * @Version V1.0
 */
@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatController extends BaseInfoProperties {
    @Resource
    private ChatMessageService chatMessageService;

    @PostMapping("/getMyUnReadCounts")
    public GraceJSONResult getMyUnReadCounts(String myId) {
        Map map = redis.hgetall(CHAT_MSG_LIST + ":" + myId);
        return GraceJSONResult.ok(map);
    }

    @PostMapping("/clearMyUnReadCounts")
    public GraceJSONResult clearMyUnReadCounts(String myId, String oppositeId) {
        redis.setHashValue(CHAT_MSG_LIST + ":" + myId, oppositeId, "0");
        return GraceJSONResult.ok();
    }

}
