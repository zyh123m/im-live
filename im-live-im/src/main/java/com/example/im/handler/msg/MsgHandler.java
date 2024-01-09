package com.example.im.handler.msg;

import com.example.im.entity.ImMsg;
import io.netty.channel.ChannelHandlerContext;

public interface MsgHandler {
    void handler(ChannelHandlerContext ctx, ImMsg msg);
}
