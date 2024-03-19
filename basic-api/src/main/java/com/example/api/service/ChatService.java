package com.example.api.service;

import com.example.api.utils.LoginUserUtils;
import com.example.oss.response.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.user.dto.UserDTO;
import org.example.user.service.UserRpcService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @DubboReference
    UserRpcService userRpcService;


    public Result friendList(String nickname){
        String username = LoginUserUtils.username();
        List<UserDTO> friendList = userRpcService.friendList(username, nickname);
        return Result.OK(friendList);
    }
}
