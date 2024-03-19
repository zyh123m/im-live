package com.example.user.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@Configuration
public class ShardingJdbcDatasourceAutoConnectConfig {


    @Bean
    public ApplicationRunner runner(DataSource dataSource){
        return args -> {
            log.info("dataSource: {}",dataSource);
            Connection connection = dataSource.getConnection();
        };
    }
}
