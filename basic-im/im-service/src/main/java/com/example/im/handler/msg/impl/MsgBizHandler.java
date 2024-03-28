package com.example.im.handler.msg.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.example.im.cache.UserChannelCache;
import com.example.im.entity.ImMsg;
import com.example.im.constant.ImMsgCodeEnum;
import com.example.im.handler.msg.MsgHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
public class MsgBizHandler implements MsgHandler {


        @Override
        public void handler(ChannelHandlerContext ctx, ImMsg msg) {
            log.info("收到 {}，消息内容为:{}", ImMsgCodeEnum.IM_BIZ_MSG.getDesc(),msg.toString());
            TextWebSocketFrame frame = new TextWebSocketFrame("服务端收到 " + ImMsgCodeEnum.IM_BIZ_MSG.getDesc() + "，消息内容为:" + msg.getBody());

            UserChannelCache.writeAllAndFlush(frame);
    }
}
