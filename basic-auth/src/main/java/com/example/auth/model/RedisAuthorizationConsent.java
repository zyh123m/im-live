package com.example.auth.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;

import java.util.Set;
@Data
@RequiredArgsConstructor
public class RedisAuthorizationConsent  {
    private  String registeredClientId;
    private  String principalName;
    private  Set<GrantedAuthority> authorities;

    public RedisAuthorizationConsent(OAuth2AuthorizationConsent consent){
        this.registeredClientId = consent.getRegisteredClientId();
        this.principalName = consent.getPrincipalName();
        this.authorities = consent.getAuthorities();
    }

    public OAuth2AuthorizationConsent parseOAuth2AuthorizationConsent(){
        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.withId(
                registeredClientId, principalName);
        return builder.authorities(x->x.addAll(authorities)).build();
    }


}
