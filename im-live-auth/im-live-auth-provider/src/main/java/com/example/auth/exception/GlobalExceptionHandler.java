package com.example.auth.exception;

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


}
