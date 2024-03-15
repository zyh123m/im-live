package com.example.api.service;

import com.example.api.utils.LoginUserUtils;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import com.example.oss.response.Result;
import org.example.user.dto.UserDTO;
import org.example.user.service.UserRpcService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @DubboReference
    UserRpcService userRpcService;
    @Resource
    LoginUserUtils loginUserUtils;

    public Result userInfo(){
        UserDTO userDTO = loginUserUtils.loginUser();
        userDTO.setPassword(null);
        return Result.OK(userDTO);
    }

    public Result updateAvatar(String avatarUrl){
        UserDTO userDTO = loginUserUtils.loginUser();
        userRpcService.updateUserAvatar(userDTO.getUsername(),avatarUrl);
        return Result.OK();
    }
}
