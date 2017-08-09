package org.sample.seckill.commons.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisClient {

    private RedisTemplate<String, String> redisTemplate;

    /**
     * 批量删除对应的value
     */
    public void remove(String... keys) {
        for (String key : keys)
            remove(key);
    }

    /**
     * 删除对应的value
     */
    public void remove(String key) {
        if (exists(key))
            redisTemplate.delete(key);
    }

    /**
     * 判断缓存中是否有对应的value
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 批量删除key
     */
    public void removePattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 读取缓存
     */
    public String get(String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 写入缓存
     */
    public void put(String key, String value) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }

    /**
     * 写入缓存
     */
    public void put(String key, String value, Long expireTime) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

}