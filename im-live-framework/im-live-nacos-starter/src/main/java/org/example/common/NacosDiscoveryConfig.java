package org.example.common;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author IPMan
 * @description: Nacos服务发现配置类
 * @date 2022/7/10
 */
@Configuration
@AutoConfigureAfter(NacosDiscoveryProperties.class)

public class NacosDiscoveryConfig {
    @Resource
    NacosDiscoveryProperties properties;

    /**
     * getServerInternetIP 通过Nginx获取本机外网IP，需要Nginx配合配置
     * @author IPMan
     * @date 2022/7/10
     *
     * @return java.lang.String 返回本机外网IP
     */
//    @Bean
//    public void getServerInternetIP(){
//        //通过配置中心地址构造查询IP请求地址
//        String url="http://nginx.im-live/getIp";
//        //外网IP
//        String internetIP="";
//        //这里一步完成了，构造一个RestTemplate对象，通过对指定URL执行GET请求来获取响应实体
//        ResponseEntity<String> response =
//                new RestTemplate()
//                        .getForEntity(url, String.class);
//        //从响应实体对象中获取内容
//        internetIP = response.getBody();
//        //调试输出，这里不推荐err的方式输出，这样仅为测试使用，推荐采用日志实现或者不输出
//        properties.setIp("www.im.com");
//
//    }





}