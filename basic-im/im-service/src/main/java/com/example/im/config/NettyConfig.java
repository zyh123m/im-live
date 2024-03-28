package com.example.im.config;


import com.example.im.handler.ImServerHandler;
import com.example.im.handler.auth.AuthHandler;
import com.example.im.initializer.WebSocketInitializer;
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
                .childHandler(new WebSocketInitializer());

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
