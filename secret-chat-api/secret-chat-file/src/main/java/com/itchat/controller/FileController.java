package com.itchat.controller;

import com.itchat.result.GraceJSONResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName FileController.java
 * @create 2024年09月09日 上午10:37
 * @Description 文件上传处理器
 * @Version V1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/uploadFace1")
    public GraceJSONResult uploadFace1(@RequestParam("file") MultipartFile file,
                                       String userId,
                                       HttpServletRequest request) throws Exception {
        // 拿到上传文件的后缀
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffixName = originalFilename.substring(index);
        // 拼接新文件的名称
        String newFileName = userId + suffixName;
        // 设置文件存储路径，可以存放到任意的指定路径
        String rootPath = "E:\\IMchat\\File" + File.separator;
        String filePath = rootPath + File.separator + "face" + File.separator + newFileName;

        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdirs();
        }
        file.transferTo(newFile);

        return GraceJSONResult.ok();
    }

}
