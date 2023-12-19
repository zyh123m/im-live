package com.example.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo
@SpringBootApplication
public class ImLiveAuthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImLiveAuthProviderApplication.class, args);
    }

}
