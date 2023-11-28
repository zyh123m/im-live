package com.example.msg;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.user.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImLiveMsgProviderApplicationTests {

    @DubboReference
    private DemoService demoService;
    @Test
    void contextLoads() {
        System.out.println(demoService.sayHello("Hello"));
    }

}
