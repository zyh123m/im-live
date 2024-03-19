package com.example.api.controller;

import com.example.api.service.UserService;
import jakarta.annotation.Resource;
import com.example.oss.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {



    @Resource
    UserService userService;

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("userInfo")
    public Result userInfo(){
        return userService.userInfo();
    }

    /**
     * 更新用户头像
     * @param avatarUrl
     * @return
     */
    @GetMapping("updateAvatar")
    public Result updateAvatar(@RequestParam("avatarUrl")String avatarUrl){
        return userService.updateAvatar(avatarUrl);
    }
}
