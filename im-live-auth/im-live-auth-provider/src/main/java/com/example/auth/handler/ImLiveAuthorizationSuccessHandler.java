package com.example.auth.handler;


import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.response.Result;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * 登录成功处理类
 *
 * @author vains
 */
public class ImLiveAuthorizationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * MappingJackson2HttpMessageConverter 是 Spring 框架提供的一个 HTTP 消息转换器，用于将 HTTP 请求和响应的 JSON 数据与 Java 对象之间进行转换
     */
    private final HttpMessageConverter<Object> accessTokenHttpResponseConverter = new MappingJackson2HttpMessageConverter();
    private Converter<OAuth2AccessTokenResponse, Map<String, Object>> accessTokenResponseParametersConverter = new DefaultOAuth2AccessTokenResponseMapConverter();


    /**
     * 自定义认证成功响应数据结构
     *
     * @param request the request which caused the successful authentication
     * @param response the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     * the authentication process.
     * @throws IOException
     * @throws
     */

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);

        if(authentication instanceof UsernamePasswordAuthenticationToken){
            Result<String> success = Result.OK();

            this.accessTokenHttpResponseConverter.write(success, null, httpResponse);

        }else{
            OAuth2AccessTokenAuthenticationToken accessTokenAuthentication =
                    (OAuth2AccessTokenAuthenticationToken) authentication;

            OAuth2AccessToken accessToken = accessTokenAuthentication.getAccessToken();
            OAuth2RefreshToken refreshToken = accessTokenAuthentication.getRefreshToken();
            Map<String, Object> additionalParameters = accessTokenAuthentication.getAdditionalParameters();

            OAuth2AccessTokenResponse.Builder builder =
                    OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
                            .tokenType(accessToken.getTokenType());
            if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
                builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
            }
            if (refreshToken != null) {
                builder.refreshToken(refreshToken.getTokenValue());
            }
            if (!CollectionUtils.isEmpty(additionalParameters)) {
                builder.additionalParameters(additionalParameters);
            }
            OAuth2AccessTokenResponse accessTokenResponse = builder.build();

            Map<String, Object> tokenResponseParameters = this.accessTokenResponseParametersConverter
                    .convert(accessTokenResponse);

            this.accessTokenHttpResponseConverter.write(Result.OK(tokenResponseParameters), null, httpResponse);
        }

    }
}
