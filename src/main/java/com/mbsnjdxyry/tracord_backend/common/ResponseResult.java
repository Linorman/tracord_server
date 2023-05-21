package com.mbsnjdxyry.tracord_backend.common;

import lombok.Data;

import java.util.HashMap;

/**
 * 结果通用类
 * @author linorman
 * @data 2023/03/13
 */
@Data
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ResponseResult<T> success(ResultCode resultCode, T data) {
        ResponseResult<T> r = new ResponseResult<>();
        r.setCode((int) resultCode.getCode());
        r.setMessage(resultCode.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> ResponseResult<T> error(ResultCode resultCode, T data) {
        ResponseResult<T> r = new ResponseResult<>();
        r.setCode((int) resultCode.getCode());
        r.setMessage(resultCode.getMsg());
        r.setData(data);
        return r;
    }
}