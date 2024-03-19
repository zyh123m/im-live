package com.example.im.constant;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public enum ImMsgCodeEnum {
    IM_LOGIN_MSG(1001,"登录消息包"),
    IM_LOGOUT_MSG(1002,"登出消息包"),
    IM_BIZ_MSG(1003,"常规业务消息包"),
    IM_COMMON_MSG(1004,"用户对用户消息包"),
    IM_HEARTBEAT_MSG(1004,"服务器心跳消息包");

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDes(String desc) {
        this.desc = desc;
    }
}
