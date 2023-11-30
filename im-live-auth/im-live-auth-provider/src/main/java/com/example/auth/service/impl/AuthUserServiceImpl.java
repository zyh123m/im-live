package com.example.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.auth.service.AuthUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.common.response.Result;
import org.example.common.utils.PhoneNumberUtils;
import org.example.user.dto.UserDTO;
import org.example.user.service.UserRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService {



    @Autowired
    PasswordEncoder passwordEncoder;


    @DubboReference
    UserRpcService userRpcService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = null;
        if (PhoneNumberUtils.isMobile(username)) {
            userDTO = userRpcService.getUserByUsername(username);
            if (userDTO == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
        }

        return User.withUsername(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .authorities("user")
                .roles("USER","message.read","message.write")
                .build();

    }


    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public Result userInfo() {
        return null;
    }






}
