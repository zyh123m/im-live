package com.example.im.handler;

import com.alibaba.fastjson2.JSON;
import com.example.im.cache.UserChannelCache;
import com.example.im.entity.ImMsg;
import com.example.im.handler.msg.ImMsgHandlerFactory;
import com.example.im.handler.msg.impl.ImMsgHandlerFactoryImpl;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.AttributeKey;

import java.io.FileOutputStream;

public class ImServerHandler  extends SimpleChannelInboundHandler{



    private ImMsgHandlerFactory handlerFactory = new ImMsgHandlerFactoryImpl();



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object frame) throws Exception {
        if(frame instanceof TextWebSocketFrame){
            textWebSocketFrame(ctx, (TextWebSocketFrame) frame);
        }else if(frame instanceof WebSocketFrame){ //websocket帧类型 已连接
            handleWebSocketFrame(ctx, (WebSocketFrame) frame);
        }

    }

    /**
     * 处理图片等二进制消息
     * @param ctx
     * @param frame
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if(frame instanceof BinaryWebSocketFrame){
            //返回客户端
            BinaryWebSocketFrame imgBack= (BinaryWebSocketFrame) frame.copy();

            //这里需要处理
            Channel channel = ctx.channel();
            channel.writeAndFlush(imgBack.retain());
            //保存服务器
            BinaryWebSocketFrame img= (BinaryWebSocketFrame) frame;
            ByteBuf byteBuf=img.content();
            try {
                FileOutputStream outputStream=new FileOutputStream("D:\\a.jpg");
                byteBuf.readBytes(outputStream,byteBuf.capacity());
                byteBuf.clear();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理文本信息
     * @param ctx
     * @param frame
     */
    private void textWebSocketFrame(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        String text = frame.text();
        ImMsg msg = JSON.parseObject(text, ImMsg.class);
        handlerFactory.handler(ctx,msg);
    }




    /**
     * 当客户端断开服务端之后（断开连接，如关闭浏览器窗口）
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        AttributeKey<Object> key = AttributeKey.valueOf("username");
        String username = (String) ctx.attr(key).get();
        UserChannelCache.remove(username,ctx.channel());
        System.out.println("客户端断开，channel对应的长id为：" + ctx.channel().id().asLongText());
    }

}
