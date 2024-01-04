package com.example.auth.constant;


import java.io.File;

/**
 * Redis相关常量
 *
 * @author vains
 */
public class RedisConstants {

    public static final Long DEFAULT_TIMEOUT_SECONDS=1000L;



    /**
     * 验证码过期时间，默认五分钟
     */
    public static final long CAPTCHA_TIMEOUT_SECONDS = 60L * 5;

    public static final String CONSENT_KEY="consent";

    public static final String FOLDER_SEPARATOR=":";


}
