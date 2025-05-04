package com.itchat.wx.entity;

import lombok.Data;

/**
 * 二维码ticket实体类
 */
@Data
public class QrCodeTicket {
    /**
     * 获取的二维码ticket
     */
    private String ticket;
    
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
     */
    private Integer expireSeconds;
    
    /**
     * 二维码图片解析后的地址
     */
    private String url;
    
    /**
     * 错误码
     */
    private Integer errcode;
    
    /**
     * 错误信息
     */
    private String errmsg;
} 