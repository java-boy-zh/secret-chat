package com.itchat.controller;

import com.itchat.common.BaseInfoProperties;
import com.itchat.result.GraceJSONResult;
import com.itchat.tasks.SMSTask;
import com.itchat.utils.IPUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName PassportController.java
 * @create 2024年09月06日 上午9:21
 * @Description 认证授权控制层
 * @Version V1.0
 */
@RestController
@RequestMapping("/passport")
@Slf4j
public class PassportController extends BaseInfoProperties {

    @Resource
    private SMSTask smsTask;

    @GetMapping("/getSMSCode")
    public GraceJSONResult getSMSCode(String mobile,
                                      HttpServletRequest request)
            throws Exception {
        // 对mobile进行校验
        if (StringUtils.isBlank(mobile)) {
            return GraceJSONResult.error();
        }

        // 对于一个ip/手机号60秒内只能获得一次验证码
        String userIp = IPUtil.getRequestIp(request);
        redis.setnx60s(MOBILE_SMSCODE + ":" + userIp, mobile);

        // 生成验证码
        String code = (int) (Math.random() * (9 + 1) * 100000) + "";
        smsTask.sendSMSInTask(mobile, code);
        redis.set(MOBILE_SMSCODE + ":" + mobile, code, 30 * 60);

        return GraceJSONResult.ok();
    }

}
