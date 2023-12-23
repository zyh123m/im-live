package com.example.api.controller;

import com.example.api.service.UserService;
import com.example.api.utils.LoginUserUtils;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.common.response.Result;
import org.example.user.dto.UserDTO;
import org.example.user.service.UserRpcService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
