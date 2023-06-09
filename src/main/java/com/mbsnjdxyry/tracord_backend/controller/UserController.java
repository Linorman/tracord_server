package com.mbsnjdxyry.tracord_backend.controller;


import com.mbsnjdxyry.tracord_backend.common.ResponseResult;
import com.mbsnjdxyry.tracord_backend.common.ResultCode;
import com.mbsnjdxyry.tracord_backend.domain.User;
import com.mbsnjdxyry.tracord_backend.exception.SystemException;
import com.mbsnjdxyry.tracord_backend.service.UserService;
import com.mbsnjdxyry.tracord_backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

//自定义登录接口
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @return
     */
    @PostMapping("/register")
    public ResponseResult userRegister(@RequestBody User user) {
        return userService.userRegister(user);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseResult userLogin(@RequestBody User user) {
        //提示必须要传用户名
        if(!StringUtils.hasText(user.getAccount())){
            //没传用户名则抛出异常, 交给 GlobalExceptionHandler 处理
            throw new SystemException(ResultCode.REQUIRE_USERNAME);
        }
        return userService.userLogin(user);
    }

    /**
     * 用户通过手机号登录
     *
     * @param account
     * @return
     */
    @PostMapping("/loginByAccount")
    public ResponseResult userLoginByAccount(String account) {
        //提示必须要传账号
        if(!StringUtils.hasText(account)){
            //没传账号则抛出异常, 交给 GlobalExceptionHandler 处理
            throw new SystemException(ResultCode.REQUIRE_USERNAME);
        }
        return userService.userLoginByAccount(account);
    }


    /**
     * 用户退出
     *
     * @return
     */
    @PostMapping("/logout")
    public ResponseResult userLogout() {
        return userService.userLogout();
    }

    /**
     * 查询用户信息
     *
     * @return
     */
    @GetMapping("/userInfo")
    public ResponseResult getUserInfo() {
        return userService.getUserInfo();
    }


    /**
     * 用户信息修改
     *
     * @return
     */
    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    /**
     * 关注用户
     * @param followerId
     * @return ResponseResult
     */
    @PostMapping("/followUser")
    public ResponseResult followUser(Integer followerId) {
        Integer userId = SecurityUtils.getUserId();
        return userService.followUser(userId, followerId);
    }

    /**
     * 取消关注用户
     * @param followerId
     * @return ResponseResult
     */
    @PostMapping("/unfollowUser")
    public ResponseResult unfollowUser(Integer followerId) {
        Integer userId = SecurityUtils.getUserId();
        return userService.unfollowUser(userId, followerId);
    }

    /**
     * 获取用户关注列表
     * @return ResponseResult
     */
    @PostMapping("/getUserFollowList")
    public ResponseResult getUserFollowList() {
        Integer userId = SecurityUtils.getUserId();
        return userService.getUserFollowList(userId);
    }

    /**
     * 获取用户关注数
     * @return ResponseResult
     */
    @PostMapping("/getUserFollowCount")
    public ResponseResult getUserFollowCount() {
        Integer userId = SecurityUtils.getUserId();
        return userService.getUserFollowCount(userId);
    }

    /**
     * 获取用户关注用户所有帖子
     * @return ResponseResult
     */
    @GetMapping("/getUserFollowPost")
    public ResponseResult getUserFollowPost() {
        return userService.getUserFollowPassage();
    }
}

