package com.example.user.demos.web;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.user.DemoService;

@DubboService
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}