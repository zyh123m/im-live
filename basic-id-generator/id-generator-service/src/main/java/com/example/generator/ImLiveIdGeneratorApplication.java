package com.example.generator;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableDubbo
@SpringBootApplication
@MapperScan(value = {"com.example.**.mapper*"})

public class ImLiveIdGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImLiveIdGeneratorApplication.class, args);
    }

}
