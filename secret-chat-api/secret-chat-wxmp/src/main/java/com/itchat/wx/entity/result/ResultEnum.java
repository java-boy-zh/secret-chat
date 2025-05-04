package com.itchat.wx.entity.result;

import lombok.Getter;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024-01-18
 * @Description 返回结果枚举类
 * @Version V1.0
 */
@Getter
public enum ResultEnum {

    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),

    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),

    TOKEN_EXPIRED(4001, "token已过期"),
    TOKEN_INVALID(4002, "token无效"),

    WX_ERROR(5001, "微信接口调用失败"),
    TEMPLATE_NOT_FOUND(5002, "模板不存在"),
    TEMPLATE_SEND_FAIL(5003, "模板消息发送失败"),
    INDUSTRY_SET_FAIL(5004, "行业设置失败");

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
