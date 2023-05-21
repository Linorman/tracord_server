package com.mbsnjdxyry.tracord_backend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbsnjdxyry.tracord_backend.common.ResponseResult;
import com.mbsnjdxyry.tracord_backend.common.ResultCode;
import com.mbsnjdxyry.tracord_backend.domain.LoginUser;
import com.mbsnjdxyry.tracord_backend.domain.User;
import com.mbsnjdxyry.tracord_backend.domain.vo.UserLoginVo;
import com.mbsnjdxyry.tracord_backend.exception.SystemException;
import com.mbsnjdxyry.tracord_backend.mapper.UserMapper;
import com.mbsnjdxyry.tracord_backend.service.UserService;
import com.mbsnjdxyry.tracord_backend.utils.JwtUtils;
import com.mbsnjdxyry.tracord_backend.utils.RedisCache;
import com.mbsnjdxyry.tracord_backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Objects;

import static com.mbsnjdxyry.tracord_backend.common.RedisConstants.USER_LOGIN;
import static com.mbsnjdxyry.tracord_backend.common.ResultCode.*;


/**
 * @author Tony贾维斯
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2022-11-08 09:21:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 用户注册
     * @return
     * @param user
     */
    @Override
    public ResponseResult userRegister(User user) {
//        1.数据非空校验
        if (!StringUtils.hasText(user.getAccount())
                || !StringUtils.hasText(user.getPassword())
                ){
            //throw new SystemException(ResultCode.REGISTER_NOT_NULL);
            return ResponseResult.error(REGISTER_NOT_NULL,null);
        }
//        2.数据是否存在校验
        if (!judgeAccount(user.getAccount())){
            //throw new SystemException(ResultCode.ACCOUNT_EXIST);
            return ResponseResult.error(ACCOUNT_EXIST,null);
        }

//        3.密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
//        4.添加用户
        return ResponseResult.success(REGISTER_SUCCESS,null);
    }

    /**
     * 用户登录
     *
     * @return
     */
    @Override
    public ResponseResult userLogin(User user) {
//        1.根据username和password封装Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
//        2.AuthenticationManager调用authenticate方法，再调用UserDetailsService的loadUserByUsername方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        3.判断认证是否通过
        if (Objects.isNull(authenticate)) {
            //throw new RuntimeException("用户名或密码错误！");
            return ResponseResult.error(LOGIN_ACCOUNT_OR_PASSWORD_ERROR,null);
        }
//        4.获取到用户id
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
//          4.1生成JWT
        String jwt = JwtUtils.createJWT(userId);

//        5.将用户信息存入缓存
        redisCache.setCacheObject(USER_LOGIN + userId, loginUser);
//
//        7.将UserInfo对象封装为BlogUserLoginVo对象
        UserLoginVo userLoginVo = new UserLoginVo(jwt, user);
//        7.将token和用户信息封装返回
        return ResponseResult.success(LOGIN_SUCCESS,userLoginVo);
    }

    /**
     * 用户通过账号登录
     *
     * @return
     */
    @Override
    public ResponseResult userLoginByAccount(String account){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,account);
        User one = getOne(queryWrapper);
//        3.判断用户是否存在
        if (Objects.isNull(one)) {
            //如果不存在就直接注册
            one = new User();
            one.setAccount(account);
            //默认密码为 123456
            one.setPassword("123456");
            userRegister(one);

            //从数据库中取回id
            LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(User::getAccount,account);
            one = getOne(queryWrapper1);
            //将token和用户信息封装返回
            //return ResponseResult.success(LOGIN_SUCCESS,registerUser);
        }
//        4.获取到用户id
        String userId = one.getId().toString();
//          4.1生成JWT
        String jwt = JwtUtils.createJWT(userId);

        LoginUser loginUser = new LoginUser(one,null);

//        5.将用户信息存入缓存
        redisCache.setCacheObject(USER_LOGIN + userId, loginUser);
//
//        6.将UserInfo对象封装为BlogUserLoginVo对象
        UserLoginVo userLoginVo = new UserLoginVo(jwt, one);
//        7.将token和用户信息封装返回
        return ResponseResult.success(LOGIN_SUCCESS,userLoginVo);
    }


    /**
     * 用户退出
     *
     * @return
     */
    public ResponseResult userLogout() {
//        1.获取到SecurityContextHolder中的LoginUser对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        2.获取到userId
        Integer userId = loginUser.getUser().getId();
//        3.redis中删除缓存
        redisCache.deleteObject(USER_LOGIN + userId);
        return ResponseResult.success(LOGOUT_SUCCESS,null);
    }

    /**
     * 查询用户信息
     *
     * @return
     */
    @Override
    public ResponseResult getUserInfo() {
//        1.获取到当前用户信息
        Integer userId = SecurityUtils.getUserId();
        User user = getById(userId);

        return ResponseResult.success(SUCCESS,user);
    }

    /**
     * 用户修改信息
     *
     * @param user
     * @return
     */
    @Override
    public ResponseResult updateUserInfo(User user) {
//        1.更新数据库
        //1.1先将密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean result = updateById(user);
        if (!result) {
            return ResponseResult.error(UPDATE_FAILURE,null);
        }
//        2.删除缓存
        redisCache.deleteObject(USER_LOGIN + user.getId());
//        3.查询新的用户信息
        User newUser = getById(user.getId());
        LoginUser loginUser = new LoginUser(newUser,null);
//        4.将用户信息存入缓存
        redisCache.setCacheObject(USER_LOGIN + user.getId(), loginUser);
        return ResponseResult.success(UPDATE_SUCCESS,null);
    }

    /**
     * 判断用户名是否存在
     * @param account
     * @return
     */
    public boolean judgeAccount(String account){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getAccount, account);
        User user = getOne(queryWrapper);
        if (Objects.isNull(user)){
            return true;
        }else{
            return false;
        }
    }
}





