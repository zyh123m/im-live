package com.example.im.entity;

import com.example.common.ImConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@RequiredArgsConstructor
public class ImMsg implements Serializable {

    private static final long serialVersionUID = 1L;
    //基本校验
    private short magic;
    //用于表示当前消息的作用，后续会交给不同的handler处理
    private  int code;
    //消息体长度
    private int length;
    //消息体
    private Object body;

    public ImMsg(int code, byte[] body) {
        this.code = code;
        this.body = body;
        this.magic = ImConstants.DEFAULT_MAGIC;
        this.length = body.length;
    }

    public short getMagic() {
        return magic;
    }

    public void setMagic(short magic) {
        this.magic = magic;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ImMsg{" +
                "magic=" + magic +
                ", code=" + code +
                ", length=" + length +
                ", body=" + body +
                '}';
    }
}
