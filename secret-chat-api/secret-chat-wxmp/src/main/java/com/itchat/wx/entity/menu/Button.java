package com.itchat.wx.entity.menu;

import lombok.Data;

import java.util.List;

/**
 * 微信公众号自定义菜单按钮实体类
 *
 * type可选值：
 * - click：点击推事件
 * - view：跳转URL
 * - miniprogram：小程序
 * - scancode_push：扫码推事件
 * - scancode_waitmsg：扫码推事件且弹出消息接收中
 * - pic_sysphoto：系统拍照发图
 * - pic_photo_or_album：拍照或者相册发图
 * - pic_weixin：微信相册发图
 * - location_select：发送位置
 * - media_id：下发消息
 * - view_limited：跳转图文消息URL
 */
@Data
public class Button {
    /**
     * 菜单的响应动作类型
     */
    private String type;

    /**
     * 菜单标题，不超过16个字节
     */
    private String name;

    /**
     * click等点击类型必须，菜单KEY值，用于消息接口推送
     */
    private String key;

    /**
     * view类型必须，网页链接，用户点击菜单可打开链接
     */
    private String url;

    /**
     * media_id类型和view_limited类型必须，调用新增永久素材接口返回的合法media_id
     */
    private String media_id;

    /**
     * miniprogram类型必须，小程序的appid
     */
    private String appid;

    /**
     * miniprogram类型必须，小程序的页面路径
     */
    private String pagepath;

    /**
     * 二级菜单数组，个数应为1~5个
     */
    private List<Button> sub_button;
}
