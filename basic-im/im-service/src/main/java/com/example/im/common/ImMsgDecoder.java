package com.example.im.common;

import com.example.im.ImConstants;
import com.example.im.entity.ImMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ImMsgDecoder extends ByteToMessageDecoder {

    public static final int BASE_LEN=2+4+4;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {

        if (byteBuf.readableBytes()>=BASE_LEN) {
            if (ImConstants.DEFAULT_MAGIC != byteBuf.readShort()) {
                ctx.close();
            }else {
                int code = byteBuf.readInt();
                int length = byteBuf.readInt();
                if (byteBuf.readableBytes()<length) {
                    ctx.close();
                }
                byte[] body = new byte[length];
                byteBuf.readBytes(body);
                ImMsg msg = new ImMsg();
                msg.setMagic(ImConstants.DEFAULT_MAGIC);
                msg.setCode(code);
                msg.setLength(length);
                msg.setBody(body);

                list.add(msg);

            }
        }
    }
}
