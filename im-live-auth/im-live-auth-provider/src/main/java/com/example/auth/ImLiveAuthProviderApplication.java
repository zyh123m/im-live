package com.example.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = {"com.example.**.mapper*"})
@EnableDubbo
@SpringBootApplication
public class ImLiveAuthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImLiveAuthProviderApplication.class, args);
    }

}
