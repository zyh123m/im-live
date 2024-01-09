package com.example.im.cache;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.ImmediateEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class UserChannelCache {

    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    private static Lock lock = new ReentrantLock();



    //TODO 这种方式无法满足分布式集群的功能
    // 如果通过分布式集群的方式负载均衡访问，
    // 如果能通过固定算法将指定的用户绑定到具体的server的话就可以但是如果需要广播功能的话就不行了
    // 这里只存储了部分用户的channel并不是全部的
    // 暂时想了一个方案 通过nacos获取集群中的服务 并轮询分发
    public static ConcurrentHashMap<String,ChannelGroup> cacheMap = new ConcurrentHashMap<>();


    /**
     * 此处使用set集合是为了满足用户多端设备信息同时推送的需求
     */

    public static void put(String userId, Channel channel) {

        ChannelGroup channelGroup = cacheMap.get(userId);
        if (channelGroup == null) {
            channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
            channelGroup.add(channel);
            cacheMap.put(userId,channelGroup);
        }else{
            channelGroup.add(channel);
        }

    }

    public static void remove(String userId,Channel channel){
        ChannelGroup channelGroup = null;
        try {
            lock.lock();

            channelGroup = cacheMap.get(userId);
            if (channelGroup != null) {
                if (channelGroup.size()==1){
                    cacheMap.remove(userId);
                }else{
                    channelGroup.remove(channel);

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }

    }

    public static Set<Channel> getByUserId(String userId){
        return cacheMap.get(userId);
    }


    public static void  writeAndFlush(String userId, Object object)  {

        ChannelGroup channelGroup = cacheMap.get(userId);
        if (channelGroup != null) {
            channelGroup.writeAndFlush(object);
        }

    }
    public static void  writeAllAndFlush(Object object)  {
        ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
        cacheMap.forEach((k,v)->channelGroup.addAll(v));
        channelGroup.writeAndFlush(object);
    }




}
