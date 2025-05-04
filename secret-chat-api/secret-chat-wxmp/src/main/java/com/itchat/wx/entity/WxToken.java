package com.itchat.wx.entity;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 19:36
 * @Description Token
 * @Version V1.0
 */
public class WxToken {

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 过期时间
     */
    private long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresTime) {
        this.expiresIn = System.currentTimeMillis() + expiresTime * 1000;
    }

    // 判断是否过期
    public boolean isExpired() {
        return System.currentTimeMillis() > this.expiresIn;
    }
}
