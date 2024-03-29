package com.example.auth.api;

import com.example.oss.response.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/error")
    public Result error(){
        return Result.error(500,"内部错误");
    }


    @PostMapping("/hello")
    public Result hello(){
        return Result.OK("test success");
    }
}
