package com.mbsnjdxyry.tracord_backend.common;

import com.alibaba.fastjson.JSONObject;
import com.mbsnjdxyry.tracord_backend.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权异常处理器
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = ResponseResult.error(ResultCode.NO_OPERATOR_AUTH,null);
        WebUtils.renderString(response, JSONObject.toJSONString(result));
    }
}
