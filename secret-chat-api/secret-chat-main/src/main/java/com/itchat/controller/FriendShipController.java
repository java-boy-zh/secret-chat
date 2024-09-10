package com.itchat.controller;

import com.itchat.bo.ContactsBO;
import com.itchat.common.BaseInfoProperties;
import com.itchat.pojo.Friendship;
import com.itchat.result.GraceJSONResult;
import com.itchat.service.FriendshipService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName FriendRequestController.java
 * @create 2024年09月10日 下午5:52
 * @Description 朋友关系控制器
 * @Version V1.0
 */
@RestController
@RequestMapping("/friendship")
@Slf4j
public class FriendShipController extends BaseInfoProperties {

    @Resource
    private FriendshipService friendshipService;

    @PostMapping("/getFriendship")
    public GraceJSONResult pass(String friendId,
                                HttpServletRequest request) {

        String myId = request.getHeader(HEADER_USER_ID);

        Friendship friendship = friendshipService.getFriendship(myId, friendId);
        return GraceJSONResult.ok(friendship);
    }
    @PostMapping("/queryMyFriends")
    public GraceJSONResult queryMyFriends(HttpServletRequest request) {
        String myId = request.getHeader(HEADER_USER_ID);
        List<ContactsBO> contactsBOs = friendshipService.queryMyFriends(myId, false);
        return GraceJSONResult.ok(contactsBOs);
    }


}
