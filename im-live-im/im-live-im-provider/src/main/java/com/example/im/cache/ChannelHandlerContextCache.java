package com.example.im.cache;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelHandlerContextCache {

    public static  Map<Long, ChannelHandlerContext> cacheMap = new ConcurrentHashMap<>();
    public static Map<Long, ChannelHandlerContext> getCacheMap() {
        return cacheMap;
    }

    public static void setCacheMap(Map<Long, ChannelHandlerContext> cacheMap) {
        ChannelHandlerContextCache.cacheMap = cacheMap;
    }

    public static void put(Long userId,ChannelHandlerContext ctx) {
        cacheMap.put(userId, ctx);
    }

    public static void remove(Long userId){
        cacheMap.remove(userId);
    }

}
