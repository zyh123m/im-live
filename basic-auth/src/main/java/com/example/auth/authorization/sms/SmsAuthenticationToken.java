package com.example.auth.authorization.sms;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SmsAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    /**
     * 令牌申请访问范围
     */
    private final Set<String> scopes;


    public static final AuthorizationGrantType GRANT_TYPE_SMS_CODE = new AuthorizationGrantType("sms_code");


    protected SmsAuthenticationToken( Authentication clientPrincipal, Map<String, Object> additionalParameters, Set<String> scopes) {
        super(GRANT_TYPE_SMS_CODE, clientPrincipal, additionalParameters);
        this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
    }

    /**
     * 用户凭证(密码)
     */
    @Override
    public Object getCredentials() {
        return this.getAdditionalParameters().get("sms_code");
    }

    public Set<String> getScopes() {
        return scopes;
    }
}
