package com.example.user;

import com.example.user.entity.User;
import com.example.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ImLiveUserApplicationTests {


    @Autowired
    UserService userService;


    @Test
    void test(){
        List<User> userList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            User user = new User();
            //user.setId((long)i);
            user.setName("用户" + i);
            user.setUsername("username" + i);
            user.setPassword("password"+i);
            user.setEmail("user" + i + "@example.com");
            user.setPhone("18888888888");
            user.setGender(random.nextInt(2) + 1);
            user.setAge(random.nextInt(60));
            user.setAvatar("https://avatar.png");
            user.setLastLoginTime(new Date());
            user.setStatus(1);
            user.setSource(random.nextInt(4) + 1);
            //user.setReferrer((long)random.nextInt(i));
            user.setIsVip(random.nextInt(2));
            user.setVipExpireTime(new Date());
            user.setProvince("广东省");
            user.setCity("深圳市");
            user.setRegion("南山区");
            user.setBalance(new BigDecimal(random.nextDouble() * 10000));
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());

            userList.add(user);
        }

        // 调用MyBatis Plus的saveBatch方法批量插入
        userService.saveBatch(userList);
    }
}
