package com.example.user.service;

import com.example.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.user.dto.UserDTO;

import java.util.List;

/**
* @author 13057
* @description 针对表【user】的数据库操作Service
* @createDate 2023-11-29 15:56:15
*/
public interface UserService extends IService<User> {

    List<UserDTO> friendList(String username, String nickname);
}
