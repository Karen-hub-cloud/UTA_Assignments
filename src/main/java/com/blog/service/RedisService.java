package com.blog.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author tiankun <tiankun@kuaishou.com>
 * Created on 2022-06-20
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection conn) throws DataAccessException {
                byte[] keyb = key.getBytes();
                byte[] valueb = toByteArray(value);
                conn.set(keyb, valueb);
                if (null != expiredTime) {
                    conn.expire(keyb, expiredTime);
                    // 以下方法我没有亲测，所以使用时请先测试下哈。我只是根据jedis里推断出来的，应该是对jedis里的方法的进行了封装。
                    //					conn.expire(key, seconds)  // 多少秒后过期
                    //					conn.expireAt(key, unixTime) // 在什么时候过期，即：System.currentTimeMillis()/1000 + 多少s
                    //					conn.pExpire(key, millis) // 多少毫秒后过期
                    //					conn.pExpireAt(key, unixTimeInMillis) // 在什么时候过期，即：System.currentTimeMillis() + 多少ms
                }
                return 1L;
            }
        });
    }

    /**
     * 获取
     */
    public Object get(final String key) {
        Object value;
        value = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection conn) throws DataAccessException {
                byte[] keyb = key.getBytes();
                byte[] valueb = conn.get(keyb);
                return toObject(valueb);
            }
        });
        return value;
    }

    /**
     * 将byte[] 转成 Object
     *
     * @param bytes
     * @return
     */
    private Object toObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * object 转成 byte[]
     * @param obj
     * @return
     */
    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
