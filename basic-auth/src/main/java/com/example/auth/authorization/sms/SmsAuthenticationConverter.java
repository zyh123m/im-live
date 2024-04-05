package com.example.auth.authorization.sms;



import com.example.auth.authorization.password.PasswordAuthenticationToken;
import com.example.auth.constant.SecurityConstants;
import com.example.auth.util.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.*;

import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.ERROR_URI;

/**
 * 短信验证码登录Token转换器
 *
 * @author vains
 */
public class SmsAuthenticationConverter implements AuthenticationConverter {


    @Override
    public Authentication convert(HttpServletRequest request) {
        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!SecurityConstants.GRANT_TYPE_SMS_CODE.equals(grantType)) {
            return null;
        }

        // 这里目前是客户端认证信息
        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        // 获取请求中的参数
        MultiValueMap<String, String> parameters = SecurityUtils.getParameters(request);

        // scope (OPTIONAL)
        String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        if (StringUtils.hasText(scope) &&
                parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
            SecurityUtils.throwError(OAuth2ErrorCodes.SERVER_ERROR,"scope 参数不合法", ERROR_URI);
        }
        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(
                    Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }

        // Mobile phone number (REQUIRED)
        String username = parameters.getFirst("phone");
        if (!StringUtils.hasText(username) || parameters.get("phone").size() != 1) {
            SecurityUtils.throwError(OAuth2ErrorCodes.SERVER_ERROR,"phone 参数不合法", ERROR_URI);
        }

        // SMS verification code (REQUIRED)
        String password = parameters.getFirst("captcha");
        if (!StringUtils.hasText(password) || parameters.get("captcha").size() != 1) {
            SecurityUtils.throwError(OAuth2ErrorCodes.SERVER_ERROR,"captcha 参数不合法", ERROR_URI);

        }

        // 提取附加参数
        Map<String, Object> additionalParameters = new HashMap<>();
        parameters.forEach((key, value) -> {
            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) &&
                    !key.equals(OAuth2ParameterNames.CLIENT_ID)) {
                additionalParameters.put(key, value.get(0));
            }
        });

        // 构建AbstractAuthenticationToken子类实例并返回
        return new SmsAuthenticationToken(clientPrincipal,additionalParameters,requestedScopes);
    }


}
