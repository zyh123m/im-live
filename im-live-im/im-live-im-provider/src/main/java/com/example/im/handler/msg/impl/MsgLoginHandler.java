package com.example.im.handler.msg.impl;

import com.example.im.entity.ImMsg;
import com.example.im.constant.ImMsgCodeEnum;
import com.example.im.handler.msg.MsgHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgLoginHandler implements MsgHandler {
    @Override
    public void handler(ChannelHandlerContext ctx, ImMsg msg) {
        log.info("收到 {}，消息内容为:{}", ImMsgCodeEnum.IM_LOGIN_MSG.getDesc(),msg.toString());

        ctx.writeAndFlush(msg);
    }
}
