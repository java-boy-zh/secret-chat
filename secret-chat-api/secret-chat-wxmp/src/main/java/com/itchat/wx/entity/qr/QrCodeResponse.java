package com.itchat.wx.entity.qr;

import lombok.Data;

/**
 * 微信创建二维码接口的返回结果
 */
@Data
public class QrCodeResponse {
    /**
     * 获取的二维码ticket,凭借此ticket可以在有效时间内换取二维码
     */
    private String ticket;

    /**
     * 二维码的有效时间,以秒为单位。最大不超过2592000（30天）
     */
    private Integer expire_seconds;

    /**
     * 二维码图片解析后的地址,开发者可根据该地址自行生成需要的二维码图片
     */
    private String url;
}
