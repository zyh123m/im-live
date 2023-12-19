package com.example.auth.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidParamException extends RuntimeException{

    public InvalidParamException(String message) {
        super(message);
        //log.error(message);
    }

    public InvalidParamException() {
        super();
    }
}
