package com.example.im.config;

import com.example.im.handler.ImServerHandler;
import com.example.im.handler.auth.AuthHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class NettyConfig implements InitializingBean {

    @Value("${netty.port}")
    private int port;

    @Override
    public void afterPropertiesSet() throws Exception {
        startApplication();
    }
    public  void startApplication() throws InterruptedException {
        //创建只处理连接请求的线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup(10);
        //创建只处理客户端读写业务的线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup(10);
        //创建服务段启动对象
        ServerBootstrap bootstrap = new ServerBootstrap();
        //配置参数
        bootstrap.group(bossGroup,workerGroup)
                //使用NioServerSocketChannel作为服务器的通道实现
                .channel(NioServerSocketChannel.class)
                //配置用于存放因没有空闲线程导致连接请求被暂存到队列的队列长度
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {

                        log.info("初始化连接渠道");
                       // channel.pipeline().addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
                       // channel.pipeline().addLast("logging", new LoggingHandler("DEBUG"));// 设置log监听器，并且日志级别为debug，方便观察运行流程

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

                        // =========== 以上是用于支持http协议 =========== //

                        //channel.pipeline().addLast(new AuthHandler());
                        /*
                         * websocket服务器处理的协议，用于指定给客户端连接访问的路由：/ws
                         * 本handler会帮你处理一些繁重的复杂的事
                         * 会帮你处理握手动作：handshaking（close，ping，pong）ping + pong = 心跳
                         * 对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同。
                         */
                        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws",true));

                        channel.pipeline().addLast(new ImServerHandler());

                    }
                });

        ChannelFuture channelFuture = bootstrap.bind(port).sync();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                log.info("Netty Server is started");
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }));
        channelFuture.channel().closeFuture();



    }


}
