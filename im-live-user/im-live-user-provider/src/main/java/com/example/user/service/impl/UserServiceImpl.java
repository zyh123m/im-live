package com.example.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import com.example.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 13057
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-11-29 15:56:15
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




