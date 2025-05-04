package com.itchat.wx.constant;

/**
 * 微信接口URL常量
 */
public class WeChatUrl {
    /**
     * 获取access_token的接口地址
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/stable_token";

    /**
     * 创建二维码ticket的接口地址
     */
    public static final String QRCODE_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create";

    /**
     * 通过ticket换取二维码的接口地址
     */
    public static final String SHOW_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
}
