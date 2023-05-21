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
    SYSTEM_ERROR(450,"系统错误"),
     * passage相关
     */
    PASSAGE_NOT_FOUND(401, "帖子不存在"),
    PASSAGE_LIST_NULL(402, "帖子列表为空"),
    PASSAGE_LIST_GET_SUCCESS(201, "帖子列表获取成功"),
    PASSAGE_LIST_GET_ERROR(403, "帖子列表获取失败"),
    PASSAGE_GET_SUCCESS(202, "帖子获取成功"),
    PASSAGE_GET_ERROR(404, "帖子获取失败"),
    PASSAGE_ADD_SUCCESS(203, "帖子发布成功"),
    PASSAGE_ADD_ERROR(405, "帖子添加失败"),
    PASSAGE_DELETE_SUCCESS(204, "帖子删除成功"),
    PASSAGE_DELETE_ERROR(406, "帖子删除失败"),
    PASSAGE_UPDATE_SUCCESS(205, "帖子更新成功"),
    PASSAGE_UPDATE_ERROR(407, "帖子更新失败"),
    PASSAGE_LIKE_SUCCESS(206, "帖子点赞成功"),
    PASSAGE_LIKE_ERROR(408, "帖子点赞失败"),
    PASSAGE_UNLIKE_SUCCESS(207, "帖子取消点赞成功"),
    PASSAGE_UNLIKE_ERROR(409, "帖子取消点赞失败"),
    PASSAGE_CANCEL_LIKE_ERROR(410, "帖子取消点赞失败"),
    PASSAGE_CANCEL_LIKE_SUCCESS(208, "帖子取消点赞成功"),
    PASSAGE_FOLLOWER_NUM_GET_SUCCESS(209, "帖子关注数获取成功"),
    PASSAGE_FOLLOWER_NUM_GET_ERROR(411, "帖子关注数获取失败"),
    PASSAGE_NUM_GET_SUCCESS(210, "帖子数获取成功"),
    PASSAGE_NUM_GET_ERROR(412, "帖子数获取失败");

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

