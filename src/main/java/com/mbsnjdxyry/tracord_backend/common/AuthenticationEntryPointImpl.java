package com.mbsnjdxyry.tracord_backend.common;

import com.alibaba.fastjson.JSONObject;
import com.mbsnjdxyry.tracord_backend.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常处理器
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private ResponseResult result = null;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        1.错误的凭证异常(如用户名或密码错误)
        if (authException instanceof BadCredentialsException) {
            result = ResponseResult.error(ResultCode.LOGIN_ERROR,null);
//        2.未携带token,需要先登录
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = ResponseResult.error(ResultCode.NEED_LOGIN,null);
        }else{
            result = ResponseResult.error(ResultCode.SYSTEM_ERROR,null);
        }
//        响应给前端
        WebUtils.renderString(response, JSONObject.toJSONString(result));
    }
}
