package com.itchat.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Auther 王青玄
 * Jedis 连接池工具类
 */
public class JedisPoolUtils {

    private static final String HOST = "localhost";
    private static final Integer PORT = 6379;
    private static final Integer DATABASE = 0;
    private static final String PASSWORD = "wangzh";

    private static final JedisPool jedisPool;

    static {
        //配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大连接数
        poolConfig.setMaxTotal(10);
        //最大空闲连接
        poolConfig.setMaxIdle(10);
        //最小空闲连接
        poolConfig.setMinIdle(5);
        //最长等待时间,ms
        poolConfig.setMaxWaitMillis(1500);
        //创建连接池对象
        jedisPool = new JedisPool(poolConfig,
                HOST,
                PORT,
                1000);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
