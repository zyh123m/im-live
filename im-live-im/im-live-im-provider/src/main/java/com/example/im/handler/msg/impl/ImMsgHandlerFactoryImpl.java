package com.example.im.handler.msg.impl;

import com.example.im.entity.ImMsg;
import com.example.im.constant.ImMsgCodeEnum;
import com.example.im.handler.msg.ImMsgHandlerFactory;
import com.example.im.handler.msg.MsgHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

public class ImMsgHandlerFactoryImpl implements ImMsgHandlerFactory {
    private static Map<Integer, MsgHandler> handlerMap= new HashMap<Integer, MsgHandler>();
    static {
        handlerMap.put(ImMsgCodeEnum.IM_BIZ_MSG.getCode(),new MsgBizHandler());
        handlerMap.put(ImMsgCodeEnum.IM_LOGIN_MSG.getCode(),new MsgLoginHandler());
        handlerMap.put(ImMsgCodeEnum.IM_LOGOUT_MSG.getCode(),new MsgLogoutHandler());
        handlerMap.put(ImMsgCodeEnum.IM_HEARTBEAT_MSG.getCode(),new MsgHeartBeatHandler());
    }

    @Override
    public void handler(ChannelHandlerContext ctx, ImMsg msg) {

        MsgHandler msgHandler = handlerMap.get(msg.getCode());
        if (msgHandler != null) {
            msgHandler.handler(ctx, msg);
        }else {
            throw new IllegalArgumentException("error msg code ,msg is "+ msg);
        }

    }
}
