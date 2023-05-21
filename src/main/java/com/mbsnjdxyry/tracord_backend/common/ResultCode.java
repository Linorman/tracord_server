package com.mbsnjdxyry.tracord_backend.common;
/**
 * 结果集枚举
 *
 * @author linorman
 * @data 2023/03/13
 */
public enum ResultCode {
    /**
     * 默认成功值
     */
    SUCCESS(200, "响应成功"),

    /**
     * 数据库操作
     */
    DATABASE_SUCCESS(210, "数据库操作成功"),
    UPDATE_FAILURE(460,"更新失败"),


    /**
     * 用户操作
     */
    REGISTER_SUCCESS(201,"注册成功"),
    LOGIN_SUCCESS(202,"登陆成功"),
    LOGOUT_SUCCESS(203,"登出成功"),
    UPDATE_SUCCESS(204,"更新成功"),
    NEED_LOGIN(420,"需要登陆后操作"),
    ACCOUNT_EXIST(421,"账号已存在"),
    REQUIRE_USERNAME(422,"账号不能为空"),
    NO_OPERATOR_AUTH(423,"您的权限不够"),
    LOGIN_ERROR(424,"登陆失败"),
    LOGIN_ACCOUNT_OR_PASSWORD_ERROR(425,"用户名或者密码错误"),
    USER_NOT_EXIST(425,"用户不存在"),

    /**
     * redis
     */
    REGISTER_NOT_NULL(440, "账号或者密码不能为空"),

    /**
     * oss
     */
    UPLOAD_SUCCESS(205,"上传成功"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(450,"系统错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}

