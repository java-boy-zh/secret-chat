package com.itchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName StaticResourceConfig.java
 * @create 2024年09月09日 上午10:56
 * @Description 静态资源配置映射地址
 * @Version V1.0
 */
@Configuration
public class StaticResourceConfig extends WebMvcConfigurationSupport {

    /**
     * 添加静态资源映射路径，图片、视频、音频等都放在classpath下的static中
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        /**
         * addResourceHandler: 指的是对外暴露的访问路径映射
         * addResourceLocations: 指的本地文件所在的目录
         */
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:E:/IMchat/File/");

        super.addResourceHandlers(registry);
    }
}
