package com.itchat.wx.enums;

import lombok.Getter;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2025年01月06日 16:19
 * @Description 二维码状态枚举
 * @Version V1.0
 */
@Getter
public enum StatusEnum {
    //等待用户已扫码  WAITING
    //用户已扫码     SCANNED
    //用户已确认     CONFIRMED
    WAITING("WAITING", "等待用户已扫码"),
    SCANNED("SCANNED", "用户已扫码"),
    CONFIRMED("CONFIRMED", "用户已确认"),
    CANCEL("CANCEL", "用户取消扫码");

    private String status;
    private String desc;

    private StatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
