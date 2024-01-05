package com.example.im.common;

import com.example.im.entity.ImMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * 护理消息的编码过程
 */
public class ImMsgEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object o, ByteBuf byteBuf) throws Exception {
        ImMsg msg = (ImMsg) o;
        byteBuf.writeShort(msg.getMagic());
        byteBuf.writeInt(msg.getCode());
        byteBuf.writeInt(msg.getLength());
        byteBuf.writeBytes(msg.getBody().toString().getBytes(StandardCharsets.UTF_8));


    }
}
