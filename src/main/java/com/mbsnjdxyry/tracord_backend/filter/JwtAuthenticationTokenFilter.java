package com.mbsnjdxyry.tracord_backend.filter;

import com.alibaba.fastjson.JSONObject;
import com.mbsnjdxyry.tracord_backend.common.ResponseResult;
import com.mbsnjdxyry.tracord_backend.domain.LoginUser;
import com.mbsnjdxyry.tracord_backend.utils.JwtUtils;
import com.mbsnjdxyry.tracord_backend.utils.RedisCache;
import com.mbsnjdxyry.tracord_backend.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.mbsnjdxyry.tracord_backend.common.RedisConstants.USER_LOGIN;
import static com.mbsnjdxyry.tracord_backend.common.ResultCode.NEED_LOGIN;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    /**
     * 解析token过滤器
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        1.获取请求头中的token
        String token = request.getHeader("Authorization");

        if(token != null){
            int i = token.indexOf(" ");
            token = token.substring(i + 1);
        }

        if (!StringUtils.hasText(token)) {
//            1.1没有token直接放行
            filterChain.doFilter(request, response);
            return;
        }
//        2.解析获得userId
        Claims claims = null;
        try {
            claims = JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
//            2.1token已经过期(过期时间为1h)
            ResponseResult errorResult = ResponseResult.error(NEED_LOGIN,null);
            WebUtils.renderString(response, JSONObject.toJSONString(errorResult));
            return;
        }
        String userId = claims.getSubject();

//        3.从redis中获取到用户信息
        LoginUser loginUser = redisCache.getCacheObject(USER_LOGIN + userId);
        if (Objects.isNull(loginUser)){
            //3.1缓存过期
            ResponseResult errorResult = ResponseResult.error(NEED_LOGIN,null);
            WebUtils.renderString(response, JSONObject.toJSONString(errorResult));
            return;
        }

//        TODO 4.获取用户权限
//        5.封装Authentication对象，将用户信息存入SecurityContextHolder中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}

