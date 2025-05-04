package com.itchat.wx.entity.menu;

import lombok.Data;

/**
 * 微信公众号菜单操作响应实体类
 * 用于接收微信服务器返回的操作结果
 */
@Data
public class WxMenuResponse {
    /**
     * 错误码，0表示成功
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;
}
