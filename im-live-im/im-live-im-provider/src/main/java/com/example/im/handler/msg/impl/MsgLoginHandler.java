package com.example.im.handler.msg.impl;

import com.example.im.common.ImMsg;
import com.example.im.handler.msg.MsgHandler;
import io.netty.channel.ChannelHandlerContext;

public class MsgLoginHandler implements MsgHandler {
    @Override
    public void handler(ChannelHandlerContext ctx, ImMsg msg) {
        ctx.writeAndFlush(msg);
    }
}
