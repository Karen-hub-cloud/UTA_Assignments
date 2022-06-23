package com.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

    public HashMap<String, Object> getAll(Set<String> keys){
        HashMap<String, Object> map = new HashMap<>();
        //循环
        for (String key : keys){
            //获取key对应的value
            if(redisTemplate.hasKey(key)){
                Object value = redisTemplate.opsForValue().get(key);
                map.put(key,value);
            }
        }
        return map;
    }

    public boolean del(String key){
        redisTemplate.delete(key);
        return true;
    }

    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
}
