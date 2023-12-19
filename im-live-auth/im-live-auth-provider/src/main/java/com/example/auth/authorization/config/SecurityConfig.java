package com.example.auth.authorization.config;


import com.example.auth.constant.SecurityConstants;
import com.example.auth.authorization.password.PasswordAuthenticationConverter;
import com.example.auth.authorization.password.PasswordAuthenticationProvider;
import com.example.auth.handler.ImLiveAuthorizationFailureHandler;
import com.example.auth.handler.LoginTargetAuthenticationEntryPoint;
import com.example.auth.handler.ImLiveAuthorizationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(            HttpSecurity http,
                                                                                  AuthenticationManager authenticationManager,
                                                                                  OAuth2AuthorizationService authorizationService,
                                                                                  OAuth2TokenGenerator<?> tokenGenerator



                                                                      ) throws Exception {

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

        //配置Oidc
        authorizationServerConfigurer
                .oidc(Customizer.withDefaults())
                // 让认证服务器元数据中有自定义的认证方式
                .authorizationServerMetadataEndpoint(metadata ->
                        metadata.authorizationServerMetadataCustomizer(customizer -> {
                            customizer.grantTypes(x ->
                                    List.of(
                                            SecurityConstants.GRANT_TYPE_SMS_CODE,
                                            SecurityConstants.GRANT_TYPE_PASSWORD
                                    ));
                        })
                )
                .tokenEndpoint(tokenEndpoint -> tokenEndpoint
                        .accessTokenRequestConverters(converters ->
                                converters.addAll(List.of(
                                        new PasswordAuthenticationConverter()
                                )))
                        .authenticationProviders(providers ->
                                providers.addAll(List.of(
                                        new PasswordAuthenticationProvider(tokenGenerator, authenticationManager, authorizationService))
                                ))
                        .accessTokenResponseHandler(new ImLiveAuthorizationSuccessHandler()) // 自定义成功响应
                        .errorResponseHandler(new ImLiveAuthorizationFailureHandler())
                );
        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        http.securityMatcher(endpointsMatcher)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .apply(authorizationServerConfigurer);


        http
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginTargetAuthenticationEntryPoint(SecurityConstants.LOGIN_URL),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                );
        DefaultSecurityFilterChain build = http.build();

        return build;
    }



}




