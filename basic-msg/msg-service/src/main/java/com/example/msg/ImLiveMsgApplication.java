package com.example.msg;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableDubbo
@SpringBootApplication
public class ImLiveMsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImLiveMsgApplication.class, args);
    }

}
