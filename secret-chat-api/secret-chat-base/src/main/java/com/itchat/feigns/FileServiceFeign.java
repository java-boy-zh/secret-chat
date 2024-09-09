package com.itchat.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName UserInfoServiceFeign.java
 * @create 2024年09月09日 下午6:29
 * @Description 文件业务调用
 * @Version V1.0
 */
@FeignClient(value = "secret-chat-file", path = "/file/file")
public interface FileServiceFeign {

    @PostMapping("/generatorQrCode")
    public String generatorQrCode(@RequestParam("wechatNumber") String wechatNumber,
                                  @RequestParam("userId") String userId) throws Exception;

}
