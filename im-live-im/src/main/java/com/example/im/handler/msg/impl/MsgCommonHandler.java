package com.example.im.handler.msg.impl;

import com.alibaba.fastjson2.JSONObject;
import com.example.im.cache.UserChannelCache;
import com.example.im.constant.ImMsgCodeEnum;
import com.example.im.entity.ImMsg;
import com.example.im.handler.msg.MsgHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * 用户对用户消息
 */
@Slf4j
public class MsgCommonHandler implements MsgHandler {

    public static final String KEY_TO_USER="to_user";
    public static final String KEY_MSG="msg";

    @Override
    public void handler(ChannelHandlerContext ctx, ImMsg msg) {
        log.info("收到 {}，消息内容为:{}", ImMsgCodeEnum.IM_COMMON_MSG.getDesc(),msg.toString());
        JSONObject data = (JSONObject)msg.getBody();
        String toUser = data.getString(KEY_TO_USER);
        String msgData = data.getString(KEY_MSG);
        if (toUser != null) {
            TextWebSocketFrame frame = new TextWebSocketFrame(msgData);
            UserChannelCache.writeAndFlush(toUser,frame);
        }

    }
}
