package com.example.im.imclient;

import com.example.im.entity.ImMsg;
import com.example.im.constant.ImMsgCodeEnum;
import com.example.im.common.ImMsgDecoder;
import com.example.im.common.ImMsgEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ImClientApplication {
    public static void main(String[] args) {
        EventLoopGroup event = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(event)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new ImMsgDecoder());
                        pipeline.addLast(new ImMsgEncoder());
                        //pipeline.addLast(new ObjectEncoder());

                        pipeline.addLast(new ImClientHandler());

                    }
                });
        System.out.println("Netty Client is ready");
        try {
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9090).sync();

            Channel channel = channelFuture.channel();
            for (int i = 1; i < 201; i++) {
                System.out.println(new ImMsg(ImMsgCodeEnum.IM_BIZ_MSG.getCode(), ImMsgCodeEnum.IM_BIZ_MSG.getDesc().getBytes()));
                channel.writeAndFlush(new ImMsg(ImMsgCodeEnum.IM_BIZ_MSG.getCode(), ImMsgCodeEnum.IM_BIZ_MSG.getDesc().getBytes()));
                channel.writeAndFlush(new ImMsg(ImMsgCodeEnum.IM_HEARTBEAT_MSG.getCode(), ImMsgCodeEnum.IM_HEARTBEAT_MSG.getDesc().getBytes()));
                channel.writeAndFlush(new ImMsg(ImMsgCodeEnum.IM_LOGOUT_MSG.getCode(), ImMsgCodeEnum.IM_LOGOUT_MSG.getDesc().getBytes()));
                channel.writeAndFlush(new ImMsg(ImMsgCodeEnum.IM_LOGIN_MSG.getCode(), ImMsgCodeEnum.IM_LOGIN_MSG.getDesc().getBytes()));

                Thread.sleep(3000);
            }
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            event.shutdownGracefully();
        }

    }
}
