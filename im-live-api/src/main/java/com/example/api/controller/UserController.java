package com.example.api.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.common.response.Result;
import org.example.user.dto.UserDTO;
import org.example.user.service.UserRpcService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @DubboReference
    UserRpcService userRpcService;

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("userInfo")
    public Result userInfo(){
        JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        UserDTO userByUsername = userRpcService.getUserByUsername(jwt.getClaimAsString("sub"));
        return Result.OK(userByUsername);
    }
}
