package com.example.im.config;

import com.example.im.common.ImMsgDecoder;
import com.example.im.common.ImMsgEncoder;
import com.example.im.handler.ImServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
public class NettyConfig implements InitializingBean {

    @Value("${netty.port}")
    private int port;
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

                        //设计消息体
                        //编解码器

                        channel.pipeline().addLast(new ImMsgDecoder());
                        channel.pipeline().addLast(new ImMsgEncoder());


                        //handler
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
        channelFuture.channel().closeFuture().sync();



    }

    @Override
    public void afterPropertiesSet() throws Exception {
        startApplication();

    }
}
