package com.example.im.cache;

import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Resource;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * channel无法实现序列化 所以方案废弃
 */
@Service
@Deprecated
public class ChannelHandlerContextRedisCache {

    @Resource
    Redisson redisson;
    @Resource
    RedisTemplate redisTemplate;

    public static final String CHANNEL_HANDLER_CONTEXT_KEY = "ChannelHandlerContextCache";



    /**
     * 此处使用set集合是为了满足用户多端设备信息同时推送的需求
     */

    public  void put(String userId,ChannelHandlerContext ctx) {

        Set<ChannelHandlerContext> contexts = (Set<ChannelHandlerContext>) redisTemplate.opsForHash().get(CHANNEL_HANDLER_CONTEXT_KEY, userId);
        if (contexts == null) {
            contexts = new HashSet<ChannelHandlerContext>();
        }
        contexts.add(ctx);
        redisTemplate.opsForHash().put(CHANNEL_HANDLER_CONTEXT_KEY,userId,contexts);
    }

    public  void remove(String userId,ChannelHandlerContext ctx){
        RLock lock = redisson.getLock("CtxLock");
        Set<ChannelHandlerContext> contexts = null;
        try {
            lock.lock();
            contexts = (Set<ChannelHandlerContext>) redisTemplate.opsForHash().get(CHANNEL_HANDLER_CONTEXT_KEY, userId);
            if (contexts != null) {
                if (contexts.size()==1){
                    redisTemplate.opsForHash().delete(CHANNEL_HANDLER_CONTEXT_KEY,userId);
                }else{
                    contexts = contexts.stream().filter(x->x!=ctx).collect(Collectors.toSet());
                    redisTemplate.opsForHash().put(CHANNEL_HANDLER_CONTEXT_KEY,userId,contexts);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }

    }

    public Set<ChannelHandlerContext> getByUserId(String userId){
        return (Set<ChannelHandlerContext>) redisTemplate.opsForHash().get(CHANNEL_HANDLER_CONTEXT_KEY, userId);
    }



}
