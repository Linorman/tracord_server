package com.mbsnjdxyry.tracord_backend.common;

import com.mbsnjdxyry.tracord_backend.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //从异常对象中获取提示信息封装返回
        return ResponseResult.error(ResultCode.SYSTEM_ERROR,e);
    }


    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //从异常对象中获取提示信息封装返回
        return ResponseResult.error(ResultCode.SYSTEM_ERROR,e);
    }
}
