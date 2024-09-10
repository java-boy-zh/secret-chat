package com.itchat.controller;

import com.itchat.common.BaseInfoProperties;
import com.itchat.result.GraceJSONResult;
import com.itchat.service.FriendRequestService;
import com.itchat.utils.PagedGridResult;
import com.itchat.vo.NewFriendRequestVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName FriendRequestController.java
 * @create 2024年09月10日 下午3:52
 * @Description 朋友请求控制器
 * @Version V1.0
 */
@RestController
@RequestMapping("/friendRequest")
@Slf4j
public class FriendRequestController extends BaseInfoProperties {

    @Resource
    private FriendRequestService friendRequestService;

    @PostMapping("/add")
    public GraceJSONResult add(@RequestBody @Valid NewFriendRequestVO friendRequestVO) {
        friendRequestService.addNewRequest(friendRequestVO);
        return GraceJSONResult.ok();
    }

    @PostMapping("/queryNew")
    public GraceJSONResult queryNew(HttpServletRequest request,
                                    @RequestParam(defaultValue = "1", name = "page") Integer page,
                                    @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {

        String userId = request.getHeader(HEADER_USER_ID);

        PagedGridResult result = friendRequestService.queryNewFriendList(userId,
                page,
                pageSize);
        return GraceJSONResult.ok(result);
    }

}
