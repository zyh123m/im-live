package com.example.im.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ImMsg implements Serializable {

    private static final long serialVersionUID = 1L;
    //基本校验
    private short magic;
    //用于表示当前消息的作用，后续会交给不同的handler处理
    private  int code;
    //消息体长度
    private int length;
    //消息体
    private byte[] body;




}
