package com.example.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import com.example.user.mapper.UserMapper;
import org.example.user.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 13057
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-11-29 15:56:15
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public List<UserDTO> friendList(String username, String nickname) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(User::getUsername,username);
        if (nickname != null) {
            wrapper.like(User::getName,nickname);
        }
        List<User> userList = baseMapper.selectList(wrapper);
        return userList.stream().map(x->BeanUtil.toBean(x, UserDTO.class)).collect(Collectors.toList());
    }
}




