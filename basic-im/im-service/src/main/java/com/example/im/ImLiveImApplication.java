package com.example.im;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo
@SpringBootApplication
@EnableSpringUtil
public class ImLiveImApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ImLiveImApplication.class);
        application.run(args);
    }

}
