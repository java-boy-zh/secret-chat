package com.itchat.wx.entity.menu;

import lombok.Data;

import java.util.List;

/**
 * 微信公众号自定义菜单实体类
 * 用于创建和获取自定义菜单
 */
@Data
public class MenuButton {
    /**
     * 一级菜单数组，个数应为1~3个
     */
    private List<Button> button;
}
