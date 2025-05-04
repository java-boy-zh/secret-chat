package com.itchat.wx.entity;

import lombok.Data;

/**
 * 二维码请求参数类
 */
@Data
public class QrCodeRequest {
    /**
     * 二维码类型
     * QR_SCENE为临时的整型参数值
     * QR_STR_SCENE为临时的字符串参数值
     * QR_LIMIT_SCENE为永久的整型参数值
     * QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    private String actionName;
    
    /**
     * 二维码详细信息
     */
    private ActionInfo actionInfo;
    
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
     */
    private Integer expireSeconds;
    
    @Data
    public static class ActionInfo {
        private Scene scene;
    }
    
    @Data
    public static class Scene {
        /**
         * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000
         */
        private Integer sceneId;
        
        /**
         * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
         */
        private String sceneStr;
    }
} 