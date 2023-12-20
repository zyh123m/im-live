package com.example.auth.constant;

/**
 * security 常量类
 *
 * @author vains
 */
public class SecurityConstants {

    public static final String LOGIN_URL = "http://auth.im.com/login";
    public static final String LOGIN_PATH = "/login";
    public static final String DEVICE_ACTIVATE_URI = "/activate";

    public static final String AUTHORIZE_PATH="/oauth2/authorize";
    /**
     * logout path
     */
    public static final String LOGOUT_PATH="/logout";

    /**
     * 权限在token中的key
     */
    public static final String AUTHORITIES_KEY = "authorities";

    /**
     * 自定义 grant type —— 短信验证码
     */
    public static final String GRANT_TYPE_SMS_CODE = "sms_code";
    public static final String GRANT_TYPE_PASSWORD="password";

    /**
     * 三方登录类型——Gitee
     */
    public static final String THIRD_LOGIN_GITEE = "gitee";

    /**
     * 三方登录类型——Github
     */
    public static final String THIRD_LOGIN_GITHUB = "github";

}
