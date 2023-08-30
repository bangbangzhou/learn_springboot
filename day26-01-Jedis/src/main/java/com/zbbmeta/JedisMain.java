package com.zbbmeta;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
public class JedisMain {
    //目标：使用Jedis（java redis）的java客户端操作redis数据库存储数据
    public static void main(String[] args) {

        //1.创建连接池对象JedisPool
        //创建连接池配置数据对象
        JedisPoolConfig config = new JedisPoolConfig();
        //配置最大连接数
        config.setMaxTotal(10);
        //配置用户等待超时时间
        config.setMaxWait(Duration.ofSeconds(30)); //30秒
        //实例连接池对象
        JedisPool pool = new JedisPool(config,"192.168.150.101", 6379);

        ////2.获取一个连接对象（Jedis对象）
        try (Jedis jedis = pool.getResource()) {
            //3.操作数据
            //Redis的字符串类型
            jedis.set("foo", "bar");
            System.out.println(jedis.get("foo")); // prints bar

            //Redis的Hash类型
            Map<String, String> hash = new HashMap<>();;
            hash.put("name", "John");
            hash.put("surname", "Smith");
            hash.put("company", "Redis");
            hash.put("age", "29");
            jedis.hset("user-session:123", hash);
            System.out.println(jedis.hgetAll("user-session:123"));
            // Prints: {name=John, surname=Smith, company=Redis, age=29}
        }
    }
}
