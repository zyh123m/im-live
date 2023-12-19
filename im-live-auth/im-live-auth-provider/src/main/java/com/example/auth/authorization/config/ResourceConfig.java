package com.example.auth.authorization.config;

import com.example.auth.constant.SecurityConstants;
import com.example.auth.handler.ImLiveAuthorizationFailureHandler;
import com.example.auth.handler.ImLiveAuthorizationSuccessHandler;
import com.example.auth.handler.ServerLogoutSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class ResourceConfig {
    @Resource
    CorsFilter corsFilter;
    @Resource
    UserDetailsService userDetailsService;


    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {

        http.addFilter(corsFilter);
        // 禁用 csrf 与 cors
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        // 放行静态资源
                        .requestMatchers("/error", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin(formLogin ->
                                formLogin
                                        .loginPage(SecurityConstants.LOGIN_URL)
                                        .loginProcessingUrl(SecurityConstants.LOGIN_PATH)
                                // 登录成功和失败改为写回json，不重定向了
                                .successHandler(new ImLiveAuthorizationSuccessHandler())
                                .failureHandler(new ImLiveAuthorizationFailureHandler())
                )
                .logout(formLogout -> {
                    formLogout
                            .logoutUrl(SecurityConstants.LOGOUT_PATH)
                            .logoutSuccessHandler(new ServerLogoutSuccessHandler())
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                    ;
                })
        ;


        return http.build();
    }


}



