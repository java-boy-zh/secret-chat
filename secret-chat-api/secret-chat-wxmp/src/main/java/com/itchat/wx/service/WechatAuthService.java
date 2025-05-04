package com.itchat.wx.service;

import com.itchat.wx.entity.WechatQrCode;

public interface WechatAuthService {
    /**
     * 生成微信登录二维码
     */
    WechatQrCode generateQrCode();

    /**
     * 检查扫码状态
     */
    WechatQrCode checkScanStatus(String sceneStr);

    void login(String sceneStr);

}
