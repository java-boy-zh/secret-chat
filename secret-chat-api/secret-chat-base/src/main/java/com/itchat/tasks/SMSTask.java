package com.itchat.tasks;

import com.itchat.utils.tencentsms.SMSUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName SMSTask.java
 * @create 2024年09月06日 上午9:13
 * @Description 腾讯云短信发送
 * @Version V1.0
 */
@Component
@Slf4j
public class SMSTask {

    @Resource
    private SMSUtils smsUtils;

    @Async
    public void sendSMSInTask(String mobile, String code) throws Exception {
//        smsUtils.sendSMS(mobile, code);
        log.info("异步任务中所发送的验证码为：{}", code);
    }

}
