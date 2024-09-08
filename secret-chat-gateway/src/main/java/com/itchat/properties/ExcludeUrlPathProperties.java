package com.itchat.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName ExcludeUrlPathProperties.java
 * @create 2024年09月08日 下午5:59
 * @Description 无需拦截路径对象
 * @Version V1.0
 */
@Component
@ConfigurationProperties(prefix = "exclude")
@PropertySource(value = {"classpath:excludeUrlPath.properties"}, encoding = "UTF-8")
@Data
public class ExcludeUrlPathProperties {
    private List<String> urls;
}
