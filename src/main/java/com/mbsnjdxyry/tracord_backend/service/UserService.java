package com.mbsnjdxyry.tracord_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mbsnjdxyry.tracord_backend.common.ResponseResult;
import com.mbsnjdxyry.tracord_backend.domain.User;

/**
 * @author Tony贾维斯
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2022-11-08 09:21:53
 */

public interface UserService extends IService<User> {

    ResponseResult userLogin(User user);

    ResponseResult userLoginByAccount(String account);

    ResponseResult userLogout();

    ResponseResult getUserInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult userRegister(User user);

    /**
     * 关注用户
     * @param userId
     * @param followerId
     * @return ResponseResult
     */
    public ResponseResult followUser(Integer userId, Integer followerId);

    /**
     * 取消关注用户
     * @param userId
     * @param followerId
     * @return ResponseResult
     */
    public ResponseResult unfollowUser(Integer userId, Integer followerId);

    /**
     * 获取用户关注列表
     * @param userId
     * @return ResponseResult
     */
    public ResponseResult getUserFollowList(Integer userId);

    /**
     * 获取用户关注数
     * @param userId
     * @return ResponseResult
     */
    public ResponseResult getUserFollowCount(Integer userId);
}
