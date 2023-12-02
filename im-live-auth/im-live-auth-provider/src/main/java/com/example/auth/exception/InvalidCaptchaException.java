package com.example.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 *  校验验证码异常时抛出
 *
 * @author vains
 */
@Slf4j
public class InvalidCaptchaException extends AuthenticationException {

    public InvalidCaptchaException(String msg) {
        super(msg);
        log.error(msg);
    }

}

