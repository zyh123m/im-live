package com.example.auth.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.auth.constant.RedisConstants;
import com.example.auth.model.RedisAuthorizationConsent;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;


public class ImLiveOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {

    @Resource
    RedisTemplate redisTemplate;


    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");
        int key = getId(authorizationConsent);
        RedisAuthorizationConsent redisAuthorizationConsent = new RedisAuthorizationConsent(authorizationConsent);
        redisTemplate.opsForValue().set(RedisConstants.CONSENT_KEY+RedisConstants.FOLDER_SEPARATOR+authorizationConsent.getRegisteredClientId()+RedisConstants.FOLDER_SEPARATOR+authorizationConsent.getPrincipalName()+"-"+key,redisAuthorizationConsent);
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");
        int key = getId(authorizationConsent);
        redisTemplate.delete(RedisConstants.CONSENT_KEY+RedisConstants.FOLDER_SEPARATOR+authorizationConsent.getRegisteredClientId()+RedisConstants.FOLDER_SEPARATOR+authorizationConsent.getPrincipalName()+"-"+key);
    }

    @Override
    @Nullable
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        Assert.hasText(registeredClientId, "registeredClientId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");
        int key = getId(registeredClientId, principalName);
        RedisAuthorizationConsent consent = (RedisAuthorizationConsent) redisTemplate.opsForValue().get(RedisConstants.CONSENT_KEY+RedisConstants.FOLDER_SEPARATOR+registeredClientId+RedisConstants.FOLDER_SEPARATOR+principalName+"-"+key);
        if (consent != null) {
            return consent.parseOAuth2AuthorizationConsent();
        }
        return null;
    }

    private static int getId(String registeredClientId, String principalName) {

        return Objects.hash(registeredClientId, principalName);
    }

    private static int getId(OAuth2AuthorizationConsent authorizationConsent) {
        return getId(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
    }

}
