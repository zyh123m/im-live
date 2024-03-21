package com.example.im.initializer;

import com.example.im.handler.ImServerHandler;
import com.example.im.handler.auth.AuthHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        log.info("初始化连接渠道");
         channel.pipeline().addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
         channel.pipeline().addLast("logging", new LoggingHandler("DEBUG"));// 设置log监听器，并且日志级别为debug，方便观察运行流程

//                        channel.pipeline().addLast(new ImMsgDecoder());
//                        channel.pipeline().addLast(new ImMsgEncoder());

        //设计消息体
        //编解码器
        // 通过管道，添加handler
        // websocket基于http协议，所以要有http编解码器。
        channel.pipeline().addLast(new HttpServerCodec());

        // 对写大数据流的支持
        channel.pipeline().addLast(new ChunkedWriteHandler());

        // 对httpmessage进行聚合，聚合成FullHttpRequest或FullHttpResponse。
        // 几乎在netty中的编程，都会使用到此handler。
        channel.pipeline().addLast(new HttpObjectAggregator(1024*64));

        channel.pipeline().addLast(new AuthHandler());
        // =========== 以上是用于支持http协议 =========== //
        /*
         * websocket服务器处理的协议，用于指定给客户端连接访问的路由：/ws
         * 本handler会帮你处理一些繁重的复杂的事
         * 会帮你处理握手动作：handshaking（close，ping，pong）ping + pong = 心跳
         * 对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同。
         */
        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws",true));

        channel.pipeline().addLast(new ImServerHandler());
    }
}
