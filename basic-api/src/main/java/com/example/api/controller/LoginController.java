package com.example.api.controller;

import com.example.oss.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 短信平台
 */
@Slf4j
@RestController
@RequestMapping("/sms")
public class LoginController {


    @GetMapping("sendSms")
    public Result sendSms(String phone){
        redisOperator.set(phone,"12333");
        redisOperator.setExpire(phone,300, TimeUnit.SECONDS);
        return Result.OK("获取短信验证码成功","12333");
    }
}
