package com.itchat.controller;

import com.itchat.bo.FriendCircleBO;
import com.itchat.common.BaseInfoProperties;
import com.itchat.pojo.FriendCircleLiked;
import com.itchat.result.GraceJSONResult;
import com.itchat.service.FriendCircleService;
import com.itchat.utils.PagedGridResult;
import com.itchat.vo.FriendCircleVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @PostMapping("/queryList")
    public GraceJSONResult publish(String userId,
                                   @RequestParam(defaultValue = "1", name = "page") Integer page,
                                   @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {

        if (StringUtils.isBlank(userId)) return GraceJSONResult.error();

        PagedGridResult gridResult = friendCircleService.queryList(userId, page, pageSize);

        List<FriendCircleBO> list = (List<FriendCircleBO>)gridResult.getRows();
        for (FriendCircleBO f : list) {
            String friendCircleId = f.getFriendCircleId();
            List<FriendCircleLiked> likedList = friendCircleService.queryLikedFriends(friendCircleId);
            f.setLikedFriends(likedList);

            boolean res = friendCircleService.doILike(friendCircleId, userId);
            f.setDoILike(res);
        }

        return GraceJSONResult.ok(gridResult);
    }

    @PostMapping("/like")
    public GraceJSONResult like(String friendCircleId,
                                HttpServletRequest request) {

        String userId = request.getHeader(HEADER_USER_ID);
        friendCircleService.like(friendCircleId, userId);

        return GraceJSONResult.ok();
    }

    @PostMapping("/unlike")
    public GraceJSONResult unlike(String friendCircleId,
                                  HttpServletRequest request) {

        String userId = request.getHeader(HEADER_USER_ID);
        friendCircleService.unlike(friendCircleId, userId);

        return GraceJSONResult.ok();
    }

    @PostMapping("/likedFriends")
    public GraceJSONResult likedFriends(String friendCircleId,
                                        HttpServletRequest request) {
        List<FriendCircleLiked> likedList =
                friendCircleService.queryLikedFriends(friendCircleId);
        return GraceJSONResult.ok(likedList);
    }

    @PostMapping("/delete")
    public GraceJSONResult delete(String friendCircleId,
                                  HttpServletRequest request) {

        String userId = request.getHeader(HEADER_USER_ID);
        friendCircleService.delete(friendCircleId, userId);

        return GraceJSONResult.ok();
    }

}
