package com.itchat.controller;

import com.itchat.config.MinIOConfig;
import com.itchat.feigns.UserInfoServiceFeign;
import com.itchat.result.GraceJSONResult;
import com.itchat.result.ResponseStatusEnum;
import com.itchat.utils.JsonUtils;
import com.itchat.utils.MinIOUtils;
import com.itchat.utils.QrCodeUtils;
import com.itchat.utils.file.FileUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName FileController.java
 * @create 2024年09月09日 上午10:37
 * @Description 文件上传处理器
 * @Version V1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private MinIOConfig minIOConfig;
    @Resource
    private UserInfoServiceFeign userInfoServiceFeign;

    /*老式MVC服务传输文件到本地*/
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

    @PostMapping("/uploadFace")
    public GraceJSONResult uploadFace(@RequestParam("file") MultipartFile file,
                                      String userId,
                                      HttpServletRequest request) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }
        // 拿到上传文件的后缀
        String filename = file.getOriginalFilename();   // 获得文件原始名称
        if (StringUtils.isBlank(filename)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }
        // 上传的头像地址
        // 防止占用空间 每个用户上传的头像必须名称为userId.后缀
        int index = filename.lastIndexOf(".");
        String suffixName = filename.substring(index);
        filename = "face"
                + MinIOUtils.SEPARATOR
                + userId
                + MinIOUtils.SEPARATOR
                + userId + suffixName;

        // 上传到Minio
        String faceUrl = MinIOUtils.uploadFile(minIOConfig.getBucketName(),
                filename,
                file.getInputStream(),
                true);
        // 远程调用 更新一下头像
        return userInfoServiceFeign.updateFace(userId, faceUrl);
    }

    @PostMapping("/generatorQrCode")
    public String generatorQrCode(String wechatNumber,
                                  String userId) throws Exception {

        // 构建map对象
        Map<String, String> map = new HashMap<>();
        map.put("wechatNumber", wechatNumber);
        map.put("userId", userId);

        // 把对象转换为json字符串，用于存储到二维码中
        String data = JsonUtils.objectToJson(map);

        // 生成二维码
        String qrCodePath = QrCodeUtils.generateQRCode(data);

        // 把二维码上传到minio中
        if (StringUtils.isBlank(qrCodePath)) {
            return null;
        }
        String objectName = "wechatNumber"
                + MinIOUtils.SEPARATOR
                + userId
                + MinIOUtils.SEPARATOR
                + userId + ".png";

        String imageQrCodeUrl = MinIOUtils.
                uploadFile(minIOConfig.getBucketName(),
                        objectName,
                        qrCodePath,
                        true);
        // 删除掉本地的二维码
        FileUtils.deleteFile(qrCodePath);
        return imageQrCodeUrl;
    }

    @PostMapping("/uploadFriendCircleBg")
    public GraceJSONResult uploadFriendCircleBg(@RequestParam("file") MultipartFile file,
                                        String userId) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }
        // 拿到上传文件的后缀
        String filename = file.getOriginalFilename();   // 获得文件原始名称
        if (StringUtils.isBlank(filename)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }
        // 上传的头像地址
        // 防止占用空间 每个用户上传的头像必须名称为userId.后缀
        int index = filename.lastIndexOf(".");
        String suffixName = filename.substring(index);
        filename = "friendCircleBg"
                + MinIOUtils.SEPARATOR
                + userId
                + MinIOUtils.SEPARATOR
                + userId + suffixName;

        // 上传到Minio
        String imageUrl = MinIOUtils.uploadFile(minIOConfig.getBucketName(),
                filename,
                file.getInputStream(),
                true);
        // 远程调用 更新一下头像
        return userInfoServiceFeign.updateFriendCircleBg(userId, imageUrl);
    }

    @PostMapping("/uploadChatBg")
    public GraceJSONResult uploadChatBg(@RequestParam("file") MultipartFile file,
                                                String userId) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }
        // 拿到上传文件的后缀
        String filename = file.getOriginalFilename();   // 获得文件原始名称
        if (StringUtils.isBlank(filename)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }
        // 上传的头像地址
        // 防止占用空间 每个用户上传的头像必须名称为userId.后缀
        int index = filename.lastIndexOf(".");
        String suffixName = filename.substring(index);
        filename = "chatBg"
                + MinIOUtils.SEPARATOR
                + userId
                + MinIOUtils.SEPARATOR
                + userId + suffixName;

        // 上传到Minio
        String imageUrl = MinIOUtils.uploadFile(minIOConfig.getBucketName(),
                filename,
                file.getInputStream(),
                true);
        // 远程调用 更新一下头像
        return userInfoServiceFeign.updateFriendCircleBg(userId, imageUrl);
    }

    @PostMapping("/uploadFriendCircleImage")
    public GraceJSONResult uploadFriendCircleImage(@RequestParam("file") MultipartFile file,
                                                   String userId) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }

        String filename = file.getOriginalFilename();   // 获得文件原始名称
        if (StringUtils.isBlank(filename)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }

        filename = "friendCircleImage"
                + MinIOUtils.SEPARATOR
                + userId
                + MinIOUtils.SEPARATOR
                + FileUtils.dealWithoutFilename(filename);

        String imageUrl = MinIOUtils.uploadFile(minIOConfig.getBucketName(),
                filename,
                file.getInputStream(),
                true);

        return GraceJSONResult.ok(imageUrl);
    }

}
