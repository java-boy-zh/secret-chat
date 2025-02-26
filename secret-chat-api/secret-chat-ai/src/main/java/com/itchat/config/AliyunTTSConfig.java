package com.itchat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunTTSConfig {

    @Value("${ALIYUN_AK_ID}")
    private String akId;

    @Value("${ALIYUN_AK_SECRET}")
    private String akSecret;

    @Value("${ALIYUN_APP_KEY}")
    private String appKey;

    @Value("${ALIYUN_APP_URL}")
    private String appUrl;


    // Getter方法，用于外部获取配置值
    public String getAkId() {
        return akId;
    }

    public String getAkSecret() {
        return akSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppUrl() {
        return appUrl;
    }
}
