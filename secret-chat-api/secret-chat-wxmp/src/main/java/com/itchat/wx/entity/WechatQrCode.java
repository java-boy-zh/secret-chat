package com.itchat.wx.entity;

import lombok.Data;

import java.util.Date;

@Data
public class WechatQrCode {
    private String sceneStr;
    private String ticket;
    private String qrCodeUrl;
    private String openId;
    private String status; // WAITING, SCANNED, CONFIRMED
    private Date createTime;
    private Date expireTime;
}
