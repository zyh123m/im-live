package com.example.im;

import com.example.im.entity.ImMsg;
import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerDemo {

    @Resource
    RocketMQTemplate rocketMQTemplate;
    @GetMapping("hello")
    public void hello(){
        Message<String> msg1 = MessageBuilder.withPayload("dsffds").build();
        rocketMQTemplate.send("msg",msg1);
    }
}
