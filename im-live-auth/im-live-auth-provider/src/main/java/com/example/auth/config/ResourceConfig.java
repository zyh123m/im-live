package com.example.auth.config;

import com.example.auth.constant.SecurityConstants;
import com.example.auth.handler.LoginFailureHandler;
import com.example.auth.handler.LoginSuccessHandler;
import com.example.auth.util.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class ResourceConfig {




    @Resource
    UserDetailsService userDetailsService;

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {

        // 禁用 csrf 与 cors
        http.csrf(corsFilter -> corsFilter.disable());
        http.cors(corsFilter -> corsFilter.disable());
        http.authorizeHttpRequests((authorize) -> authorize
                        // 放行静态资源
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin(formLogin ->
                        formLogin.loginPage(SecurityConstants.LOGIN_PATH)
                                // 登录成功和失败改为写回json，不重定向了
                                .successHandler(new LoginSuccessHandler())
                                .failureHandler(new LoginFailureHandler())
                );
        http.oauth2ResourceServer((resourceServer) -> resourceServer
                .jwt(Customizer.withDefaults())
                .accessDeniedHandler(SecurityUtils::exceptionHandler)
                .authenticationEntryPoint(SecurityUtils::exceptionHandler)
        );
        return http.build();
    }


}



