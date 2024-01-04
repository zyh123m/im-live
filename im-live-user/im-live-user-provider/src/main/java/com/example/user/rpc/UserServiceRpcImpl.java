package com.example.user.rpc;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.user.dto.UserDTO;
import org.example.user.service.UserRpcService;
import org.springframework.data.redis.core.RedisTemplate;

@DubboService
public class UserServiceRpcImpl implements UserRpcService {

    @Resource
    private UserService userService;

    @Resource
    RedisTemplate redisTemplate;
    @Override
    public UserDTO getUserByUsername(String username) {

        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        UserDTO userDTO = BeanUtil.toBean(user, UserDTO.class);
        redisTemplate.opsForValue().set(username,userDTO);
        return userDTO ;
    }

    @Override
    public Boolean updateUserAvatar(String username, String avatarUrl) {
        return userService.update(new LambdaUpdateWrapper<User>().set(User::getAvatar,avatarUrl).eq(User::getUsername, username));
    }
}
