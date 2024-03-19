package com.example.fetch.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb://admin:admin@192.168.101.121:27017");
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient(), "user");
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
