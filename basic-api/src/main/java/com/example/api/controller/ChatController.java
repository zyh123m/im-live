package com.example.api.controller;

import com.example.api.service.ChatService;
import com.example.oss.response.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chat")
public class ChatController {
    @Resource
    ChatService chatService;

    @GetMapping("friendList")
    public Result friendList(@RequestParam(name = "nickname",required = false)String nickname){
       return chatService.friendList(nickname);
    }

}
