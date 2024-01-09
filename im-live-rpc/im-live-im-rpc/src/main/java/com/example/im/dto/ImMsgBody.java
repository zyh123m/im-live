package com.example.im.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class ImMsgBody implements Serializable {
    private static final long serialVersionUID = 1L;

    private int appId;

    private String username;


    private String token;

    private String data;


}
