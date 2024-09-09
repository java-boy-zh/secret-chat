package com.itchat.feigns;

import com.itchat.result.GraceJSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName UserInfoServiceFeign.java
 * @create 2024年09月09日 下午6:29
 * @Description 用户信息业务调用
 * @Version V1.0
 */
@FeignClient(value = "secret-chat-main", path = "/main/userinfo")
public interface UserInfoServiceFeign {

    @PostMapping("/updateFace")
    public GraceJSONResult updateFace(@RequestParam("userId") String userId,
                                      @RequestParam("face") String face
    );

    @PostMapping("/updateFriendCircleBg")
    public GraceJSONResult updateFriendCircleBg(@RequestParam("userId") String userId,
                                         @RequestParam("imageUrl") String imageUrl);
}
