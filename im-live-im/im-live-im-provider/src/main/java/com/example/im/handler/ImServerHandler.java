package com.example.im.handler;

import com.example.im.entity.ImMsg;
import com.example.im.handler.msg.ImMsgHandlerFactory;
import com.example.im.handler.msg.impl.ImMsgHandlerFactoryImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ImServerHandler  extends SimpleChannelInboundHandler{

    private ImMsgHandlerFactory handlerFactory = new ImMsgHandlerFactoryImpl();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if (!(o instanceof ImMsg)) {
            throw new IllegalArgumentException("error message,message is {}"+o);
        }
        ImMsg msg = (ImMsg) o;

        handlerFactory.handler(channelHandlerContext,msg);

    }
}
