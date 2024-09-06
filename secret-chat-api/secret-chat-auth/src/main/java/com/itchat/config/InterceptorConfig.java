package com.itchat.config;

import com.itchat.interceptor.SMSInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName InterceptorConfig.java
 * @create 2024年09月06日 上午10:18
 * @Description 拦截器配置
 * @Version V1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 在Springboot容器中放入拦截器
     * @return
     */
    @Bean
    public SMSInterceptor smsInterceptor() {
        return new SMSInterceptor();
    }

    /**
     * 注册拦截器，并且拦截指定的路由，否则不生效
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(smsInterceptor())
                .addPathPatterns("/passport/getSMSCode");
    }
}
