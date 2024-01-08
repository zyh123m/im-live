package com.example.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.example.**"})
@EnableCaching
@MapperScan(value = {"com.example.**.mapper*"})
public class ImLiveUserApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ImLiveUserApplication.class);
        //springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

}
