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
