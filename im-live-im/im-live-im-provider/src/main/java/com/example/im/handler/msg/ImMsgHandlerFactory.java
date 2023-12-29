package com.example.im.handler.msg;

import com.example.im.common.ImMsg;
import io.netty.channel.ChannelHandlerContext;

public interface ImMsgHandlerFactory {

    void handler(ChannelHandlerContext ctx, ImMsg msg);

}
