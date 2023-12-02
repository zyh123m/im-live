package com.example.auth.exception;


import org.example.common.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResultHandler {

    @ExceptionHandler({Exception.class})
    public Result<?> paramExceptionHandler(Exception e){
        return Result.error(500,e.getMessage());
    }
}
