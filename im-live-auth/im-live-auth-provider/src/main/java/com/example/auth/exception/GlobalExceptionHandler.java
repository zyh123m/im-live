package com.example.auth.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.example.common.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author vains
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCaptchaException.class)
    public Result exception(Exception e) {
        log.error(e.getMessage());
        return Result.error(500,"Invalid Captcha");
    }


    @ExceptionHandler(InvalidParamException.class)
    public Result invalidParamException(InvalidParamException e){
        return Result.error(500,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> exception(Exception e, HttpServletRequest request) {
        log.error("接口[{}]调用失败，原因：{}", request.getRequestURI(), e.getMessage(), e);
        return Result.error(500,e.getMessage());
    }
}
