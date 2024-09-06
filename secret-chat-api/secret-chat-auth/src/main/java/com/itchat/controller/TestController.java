package com.itchat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName TestController.java
 * @create 2024年09月04日 下午12:57
 * @Description 测试控制器
 * @Version V1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/a")
    public String a() {
        return "a";
    }

}
