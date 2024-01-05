package com.example.im.handler;

import com.alibaba.fastjson2.JSON;
import com.example.im.entity.ImMsg;
import com.example.im.handler.msg.ImMsgHandlerFactory;
import com.example.im.handler.msg.impl.ImMsgHandlerFactoryImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ImServerHandler  extends SimpleChannelInboundHandler<TextWebSocketFrame>{

    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private ImMsgHandlerFactory handlerFactory = new ImMsgHandlerFactoryImpl();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame frame) throws Exception {
        String text = frame.text();
        ImMsg msg = JSON.parseObject(text, ImMsg.class);
        handlerFactory.handler(channelHandlerContext,msg);
    }


    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的channel，并且放到ChannelGroup中去进行管理。
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    /**
     * 当客户端断开服务端之后（断开连接，如关闭浏览器窗口）
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel。
        // clients.remove(ctx.channel());
        System.out.println("客户端断开，channel对应的长id为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，channel对应的短id为：" + ctx.channel().id().asShortText());
    }

}
