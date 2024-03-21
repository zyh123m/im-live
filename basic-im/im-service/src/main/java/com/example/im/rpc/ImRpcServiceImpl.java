package com.example.im.rpc;

import com.example.im.service.ImRpcService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class ImRpcServiceImpl implements ImRpcService {
    @Override
    public boolean sendMsg(String username, String msg) {
        System.out.println(username+"发送了消息："+msg);
        return true;
    }
}
