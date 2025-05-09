package com.itchat;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName ApplicationRunning.java
 * @create 2024年09月04日 下午12:55
 * @Description 认证服务启动类
 * @Version V1.0
 */
@SpringBootApplication
@Slf4j
@MapperScan(basePackages = "com.itchat.mapper")
public class ApplicationRunning {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(ApplicationRunning.class, args);
        Environment env = application.getEnvironment();
        var ip = InetAddress.getLocalHost().getHostAddress();
        var port = env.getProperty("server.port");
        var path = env.getProperty("server.servlet.context-path");
        var swaggerPath = env.getProperty("springdoc.swagger-ui.path");
        var swaggerUiEnabled = env.getProperty("springdoc.swagger-ui.enabled");
        var apiDocsEnabled = env.getProperty("springdoc.api-docs.enabled");
        log.info("(♥◠‿◠)ﾉﾞ  认证服务启动类 启动成功   ლ(´ڡ`ლ)ﾞ");
        log.info("http://" + ip + ":" + port + path + "/index");
        if ("true".equals(swaggerUiEnabled)) {
            log.info("Swagger-ui: http://" + ip + ":" + port + path + swaggerPath);
        }
        if ("true".equals(apiDocsEnabled)) {
            log.info("Knife4j-ui: http://" + ip + ":" + port + path + "/doc.html");
        }
    }
}
