package com.itchat.wx.entity.qr;

import lombok.Data;

/**
 * 创建二维码的请求参数实体类
 */
@Data
public class QrCodeRequest {
    /**
     * 二维码有效时间,以秒为单位。最大不超过2592000（30天）
     * 若不填写,则默认为永久二维码
     */
    private Integer expir_seconds;

    /**
     * 二维码类型:
     * QR_SCENE: 临时二维码
     * QR_LIMIT_SCENE: 永久二维码
     */
    private String action_name;

    /**
     * 二维码详细信息
     */
    private ActionInfo action_info;

    @Data
    public static class ActionInfo {
        /**
         * 场景值信息
         */
        private Scene scene;
    }

    @Data
    public static class Scene {
        /**
         * 场景值ID,临时二维码时为32位非0整型,永久二维码时最大值为100000
         */
        private Integer scene_id;

        /**
         * 场景值字符串,字符串类型,长度限制为1到64
         */
        private String scene_str;
    }
}
