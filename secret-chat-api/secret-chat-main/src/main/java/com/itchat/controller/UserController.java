package com.itchat.controller;

import com.itchat.bo.UsersBO;
import com.itchat.common.BaseInfoProperties;
import com.itchat.pojo.Users;
import com.itchat.result.GraceJSONResult;
import com.itchat.result.ResponseStatusEnum;
import com.itchat.service.UsersService;
import com.itchat.utils.CopyBeanUtils;
import com.itchat.vo.ModifyUserVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName TestController.java
 * @create 2024年09月04日 下午12:57
 * @Description 用户信息控制器
 * @Version V1.0
 */
@RestController
@RequestMapping("/userinfo")
public class UserController extends BaseInfoProperties {

    @Resource
    private UsersService usersService;

    @PostMapping("/modify")
    public GraceJSONResult modify(@RequestBody @Valid ModifyUserVO modifyUserVO) {
        // 修改用户信息
        usersService.modifyUserInfo(modifyUserVO);
        // 返回用户信息
        UsersBO userBO = getUserBO(modifyUserVO.getUserId(), true);
        return GraceJSONResult.ok(userBO);
    }

    @PostMapping("/get")
    public GraceJSONResult get(@RequestParam("userId") String userId) {
        return GraceJSONResult.ok(getUserBO(userId, false));
    }

    @PostMapping("/updateFace")
    public GraceJSONResult updateFace(@RequestParam("userId") String userId,
                                      @RequestParam("face") String face
    ) {
        ModifyUserVO modifyUserVO = new ModifyUserVO();
        modifyUserVO.setUserId(userId);
        modifyUserVO.setFace(face);

        // 修改用户信息
        usersService.modifyUserInfo(modifyUserVO);
        // 返回用户信息
        UsersBO userBO = getUserBO(modifyUserVO.getUserId(), true);
        return GraceJSONResult.ok(userBO);
    }

    @PostMapping("/updateFriendCircleBg")
    public GraceJSONResult updateFriendCircleBg(@RequestParam("userId") String userId,
                                                @RequestParam("imageUrl") String imageUrl
    ) {
        ModifyUserVO modifyUserVO = new ModifyUserVO();
        modifyUserVO.setUserId(userId);
        modifyUserVO.setFriendCircleBg(imageUrl);

        // 修改用户信息
        usersService.modifyUserInfo(modifyUserVO);
        // 返回用户信息
        UsersBO userBO = getUserBO(modifyUserVO.getUserId(), true);
        return GraceJSONResult.ok(userBO);
    }
    @PostMapping("/uploadChatBg")
    public GraceJSONResult updateChatBg(
            @RequestParam("userId") String userId,
            @RequestParam("imageUrl") String imageUrl) {
        ModifyUserVO modifyUserVO = new ModifyUserVO();
        modifyUserVO.setUserId(userId);
        modifyUserVO.setChatBg(imageUrl);

        // 修改用户信息
        usersService.modifyUserInfo(modifyUserVO);
        // 返回用户信息
        UsersBO userBO = getUserBO(modifyUserVO.getUserId(), true);
        return GraceJSONResult.ok(userBO);
    }

    @PostMapping("/queryFriend")
    public GraceJSONResult queryFriend(String queryString,
                                       HttpServletRequest request) {

        if (StringUtils.isBlank(queryString)) {
            return GraceJSONResult.error();
        }

        Users friend = usersService.getByWechatNumOrMobile(queryString);
        if (friend == null) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FRIEND_NOT_EXIST_ERROR);
        }

        // 判断，不能添加自己为好友
        String myId = request.getHeader(HEADER_USER_ID);
        if (myId.equals(friend.getId())) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.CAN_NOT_ADD_SELF_FRIEND_ERROR);
        }

        return GraceJSONResult.ok(friend);
    }

    private UsersBO getUserBO(String userId, boolean needToken) {
        Users userDB = usersService.getUserById(userId);
        UsersBO resultUserBO = CopyBeanUtils.copy(userDB, UsersBO.class);
        if (needToken) {
            // 生成UserToken
            String uToken = TOKEN_USER_PREFIX + SYMBOL_DOT + UUID.randomUUID();
            // 本方式只能限制用户在一台设备进行登录
//        redis.set(REDIS_USER_TOKEN + ":" + user.getId(), uToken);   // 设置分布式会话
            // 运行多台设备登录同一个账号
            redis.set(REDIS_USER_TOKEN + ":" + uToken, userId);   // 设置分布式会话

            resultUserBO.setUserToken(uToken);
        }
        return resultUserBO;
    }

}
