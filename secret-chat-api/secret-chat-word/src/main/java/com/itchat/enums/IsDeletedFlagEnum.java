package com.itchat.enums;

import lombok.Getter;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月15日 11:08
 * @Description 是否删除标记
 * @Version V1.0
 */
@Getter
public enum IsDeletedFlagEnum {
    DELETED(1, "已删除"),
    UN_DELETED(0, "未删除");

    public int code;

    public String desc;

    IsDeletedFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static IsDeletedFlagEnum getByCode(int codeVal) {
        for (IsDeletedFlagEnum resultCodeEnum : IsDeletedFlagEnum.values()) {
            if (resultCodeEnum.code == codeVal) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
