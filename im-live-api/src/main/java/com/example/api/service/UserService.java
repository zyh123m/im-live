package com.example.api.service;

import com.example.api.utils.LoginUserUtils;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.common.response.Result;
import org.example.user.dto.UserDTO;
import org.example.user.service.UserRpcService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {
    @DubboReference
    UserRpcService userRpcService;
    @Resource
    LoginUserUtils loginUserUtils;

    public Result userInfo(){
        UserDTO userDTO = loginUserUtils.loginUser();
        return Result.OK(userDTO);
    }

    public Result updateAvatar(String avatarUrl){
        UserDTO userDTO = loginUserUtils.loginUser();
        userRpcService.updateUserAvatar(userDTO.getUsername(),avatarUrl);
        return Result.OK();
    }
}
