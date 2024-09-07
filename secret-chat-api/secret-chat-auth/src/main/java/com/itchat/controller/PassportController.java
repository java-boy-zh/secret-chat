package com.itchat.controller;

import com.itchat.common.BaseInfoProperties;
import com.itchat.exceptions.GraceException;
import com.itchat.pojo.Users;
import com.itchat.result.GraceJSONResult;
import com.itchat.result.ResponseStatusEnum;
import com.itchat.service.UsersService;
import com.itchat.tasks.SMSTask;
import com.itchat.utils.IPUtil;
import com.itchat.vo.RegistUserVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Resource
    private UsersService usersService;

    /**
     * 验证码获取接口
     *
     * @param mobile
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/getSMSCode")
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


    @PostMapping("/regist")
    public GraceJSONResult regist(@RequestBody @Validated RegistUserVO registUserVO,
                                  HttpServletRequest request)
            throws Exception {

        // 首先判断用户输入验证码是否正确
        String mobile = registUserVO.getMobile();
        String smsCode = registUserVO.getSmsCode();
        String nickname = registUserVO.getNickname();
        String redisCode = redis.get(MOBILE_SMSCODE + ":" + mobile);

        if (StringUtils.isBlank(redisCode) || !redisCode.equalsIgnoreCase(smsCode)) {
            GraceException.display(ResponseStatusEnum.SMS_CODE_ERROR);
        }

        // 判断该用户是否存在
        // 存在则不能重复注册
        // 不存在则注册
        Users user = usersService.queryUserByMobileIfExist(mobile);
        if (user == null) {
            // 表示该用户未注册过 进行用户注册
            user = usersService.createUsers(mobile, nickname);
        } else {
            GraceException.display(ResponseStatusEnum.USER_ALREADY_EXIST_ERROR);
        }
        // 成功后删除验证码
        redis.del(MOBILE_SMSCODE + ":" + mobile);

        // 返回用户
        return GraceJSONResult.ok(user);
    }

}
