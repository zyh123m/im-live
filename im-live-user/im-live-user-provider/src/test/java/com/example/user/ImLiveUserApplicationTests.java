package com.example.user;

import com.example.user.entity.User;
import com.example.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ImLiveUserApplicationTests {


    @Autowired
    UserService userService;


    @Test
    void test(){
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId((long) i);
            user.setName("name"+i);
            user.setUsername("username"+i);
            user.setPassword("password"+i);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userService.save(user);

        }
    }
}
