package com.maomiyibian.microservice.api.service;

/**
 * @Author: 微笑天使
 * @Date: 2018/12/14 10:44
 * @Version 1.0
 */
public interface RedisService {


    /**
     * 普通get
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 存值
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, Object value);

    /**带过期时间的普通缓存
     * @param key
     * @param value
     * @param time
     * @return
     */
    boolean set(String key, Object value, long time);

    //========================hash散列操作部分=================

    /**
     * hash散列存储
     * @param key
     * @param item
     * @param value
     * @return
     */
     boolean hset(String key,String item,Object value);

    /**
     * hash散列存储带过期时间
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
     boolean hset(String key,String item,Object value,long time);

    /**
     * 缓存中取值
     *  @param key
     *  @param item
     *  @return
     */
     Object hget(String key,String item);

    /**
     * 设置键过期时间
     * @param key
     * @param time
     * @return
     */
     boolean expire(String key,long time);

    /**
     * 从hash散列中移除值
     * @param key
     * @param item
     */
     void hdel(String key, Object... item);
    }



