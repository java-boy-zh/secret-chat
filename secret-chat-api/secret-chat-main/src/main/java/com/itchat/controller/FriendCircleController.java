package com.itchat.controller;

import com.itchat.common.BaseInfoProperties;
import com.itchat.result.GraceJSONResult;
import com.itchat.service.FriendCircleService;
import com.itchat.vo.FriendCircleVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName FriendCircleController.java
 * @create 2024年09月11日 上午9:04
 * @Description 朋友圈控制器
 * @Version V1.0
 */
@RestController
@RequestMapping("/friendCircle")
@Slf4j
public class FriendCircleController extends BaseInfoProperties {

    @Resource
    private FriendCircleService friendCircleService;

    @PostMapping("/publish")
    public GraceJSONResult publish(
            @RequestBody FriendCircleVO friendCircleVO,
            HttpServletRequest request) {
        String userId = request.getHeader(HEADER_USER_ID);

        friendCircleVO.setUserId(userId);
        friendCircleVO.setPublishTime(LocalDateTime.now());

        friendCircleService.publish(friendCircleVO);

        return GraceJSONResult.ok();
    }

}
