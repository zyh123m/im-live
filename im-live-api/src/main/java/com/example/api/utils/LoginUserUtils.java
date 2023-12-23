package com.example.api.utils;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.user.dto.UserDTO;
import org.example.user.service.UserRpcService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUtils {
    @DubboReference
    UserRpcService userRpcService;
    public  UserDTO loginUser(){
        JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        UserDTO user = userRpcService.getUserByUsername(jwt.getClaimAsString("sub"));
        return user;
    }
}
