package com.example.auth.service;


import com.example.oss.response.Result;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserService extends UserDetailsService {


    public Result userInfo();
}
