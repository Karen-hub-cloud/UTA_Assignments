package com.blog.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author tiankun <tiankun@kuaishou.com>
 * Created on 2022-06-20
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 添加
     */
    public void put(String key, Object value) {
        put(key, value, null);
    }

    /**
     * 添加
     */
    public void put(final String key, final Object value, final Long expiredTime) {
        if(StringUtils.isBlank(key)) {
            return;
        }
        if (null == value) {
            return;
        }
        redisTemplate.opsForValue().set(key, value);
        return;
    }

    /**
     * 获取
     */
    public Object get(final String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean del(String key){
        redisTemplate.delete(key);
        return true;
    }

    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
}
