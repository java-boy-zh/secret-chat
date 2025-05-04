package com.itchat.wx.service;

/**
 * 微信token服务接口
 */
public interface WechatTokenService {
    
    /**
     * 获取access_token
     * @return access_token
     */
    String getAccessToken();
    
    /**
     * 刷新access_token
     * @return 新的access_token
     */
    String refreshAccessToken();
} 