package com.itchat.controller;

import com.itchat.bo.CommentBO;
import com.itchat.common.BaseInfoProperties;
import com.itchat.result.GraceJSONResult;
import com.itchat.service.CommentService;
import com.itchat.vo.CommentVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName CommentController.java
 * @create 2024年09月12日 下午5:49
 * @Description 评论控制器
 * @Version V1.0
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController extends BaseInfoProperties {

    @Resource
    private CommentService commentService;

    @PostMapping("/create")
    public GraceJSONResult create(@RequestBody CommentVO commentVO,
                                  HttpServletRequest request) {
        CommentBO commentBO = commentService.createComment(commentVO);
        return GraceJSONResult.ok(commentBO);
    }

    @PostMapping("/query")
    public GraceJSONResult query(String friendCircleId) {
        return GraceJSONResult.ok(commentService.queryAll(friendCircleId));
    }

    @PostMapping("/delete")
    public GraceJSONResult delete(String commentUserId,
                                  String commentId,
                                  String friendCircleId) {

        if (StringUtils.isBlank(commentUserId) ||
                StringUtils.isBlank(commentId) ||
                StringUtils.isBlank(friendCircleId)
        ) {
            return GraceJSONResult.error();
        }

        commentService.deleteComment(commentUserId, commentId, friendCircleId);
        return GraceJSONResult.ok();
    }
}
