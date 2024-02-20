package com.example.im.handler.auth;

import cn.hutool.jwt.Claims;
import com.example.im.cache.UserChannelCache;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.util.AttributeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            HttpHeaders headers = request.headers();

            String uri = request.getUri();
            int i = uri.lastIndexOf("/")+1;
            String username = uri.substring(i,uri.length());
            AttributeKey<Object> key = AttributeKey.valueOf("username");
            ctx.attr(key).set(username);
            UserChannelCache.put( username,ctx.channel());
            request.setUri("/ws");
            // 传递到下一个handler：升级握手
            ctx.fireChannelRead(request.retain());
            // 在本channel上移除这个handler消息处理，即只处理一次，鉴权通过与否
            ctx.pipeline().remove(AuthHandler.class);
        } else {
            ctx.channel().close();
        }
    }




    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
