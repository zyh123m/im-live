package com.example.auth.authorization.config;


import com.example.auth.constant.SecurityConstants;
import com.example.auth.authorization.password.PasswordAuthenticationConverter;
import com.example.auth.authorization.password.PasswordAuthenticationProvider;
import com.example.auth.handler.ImLiveExceptionEntryPoint;
import com.example.auth.handler.ImLiveAuthorizationFailureHandler;
import com.example.auth.handler.ImLiveAuthorizationSuccessHandler;
import com.example.auth.handler.ImLiveLogoutHandler;
import com.example.auth.util.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class AuthorizationSecurityConfig {

    @Resource
    CorsFilter corsFilter;
    @Resource
    UserDetailsService userDetailsService;
    public static final String JSESSIONID="JSESSIONID";
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,
                                                                      AuthenticationManager authenticationManager,
                                                                      OAuth2AuthorizationService authorizationService,
                                                                      OAuth2TokenGenerator<?> tokenGenerator) throws Exception {

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
                        }))
                .tokenEndpoint(tokenEndpoint -> tokenEndpoint
                        .accessTokenRequestConverters(converters ->
                                converters.addAll(List.of(
                                        new PasswordAuthenticationConverter()
                                )))
                        .authenticationProviders(providers ->
                                providers.addAll(List.of(
                                        new PasswordAuthenticationProvider(tokenGenerator, authenticationManager, authorizationService)
                                )))
                        .accessTokenResponseHandler(new ImLiveAuthorizationSuccessHandler()) // 自定义成功响应
                        .errorResponseHandler(new ImLiveAuthorizationFailureHandler())
                )

        ;

        basicConfig(http);

        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        http.securityMatcher(endpointsMatcher)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .apply(authorizationServerConfigurer);


        return http.build();
    }




    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {

        http.addFilter(corsFilter);
        // 禁用 csrf 与 cors
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
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
                            .logoutSuccessHandler(new ImLiveLogoutHandler())
                            .invalidateHttpSession(true)
                            .deleteCookies(JSESSIONID);
                });


        basicConfig(http);

        return http.build();
    }


    public void basicConfig(HttpSecurity http) throws Exception {
        http
                // 当未登录时访问认证端点时重定向至login页面
                .exceptionHandling((exceptions) -> exceptions
                        .accessDeniedHandler(SecurityUtils::exceptionHandler)
                        .authenticationEntryPoint(new ImLiveExceptionEntryPoint())
                )
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .accessDeniedHandler(SecurityUtils::exceptionHandler)
                        .authenticationEntryPoint(new ImLiveExceptionEntryPoint())
                        .jwt(Customizer.withDefaults())
                );
    }

}




