package com.itchat.wx.entity.result;

import lombok.Data;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024-01-18
 * @Description 统一返回结果类
 * @Version V1.0
 */
@Data
public class Result<T> {

    // 状态码
    private Integer code;

    // 返回信息
    private String message;

    // 返回数据
    private T data;

    // 时间戳
    private long timestamp;

    private Result() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功返回结果
     */
    public static <T> Result<T> ok() {
        return ok(null);
    }

    /**
     * 成功返回结果
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> fail() {
        return fail(ResultEnum.FAIL);
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> fail(ResultEnum resultEnum) {
        Result<T> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
