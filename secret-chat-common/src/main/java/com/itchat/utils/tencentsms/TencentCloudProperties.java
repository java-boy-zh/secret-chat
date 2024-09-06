package com.itchat.utils.tencentsms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Auther 王青玄
 */
@Component
@Data
@PropertySource(value = {"classpath:tencentCloud.properties"}, encoding = "UTF-8")
@ConfigurationProperties(prefix = "tencent.cloud")
public class TencentCloudProperties {

    private String secretId;
    private String secretKey;
    private String smsSdkAppId;
    private String signName = "风间影月";
    private String templateId;

}
