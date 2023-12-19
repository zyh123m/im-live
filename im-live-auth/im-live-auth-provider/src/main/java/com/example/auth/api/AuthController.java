package com.example.auth.api;

import org.example.common.response.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/error")
    public Result error(){
        return Result.error(500,"内部错误");
    }


}
