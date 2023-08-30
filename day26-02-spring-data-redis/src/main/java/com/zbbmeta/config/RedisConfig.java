package com.zbbmeta.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Configuration
public class RedisConfig {
    //目标：通过@Bean创建对象加入IOC容器，这里手动创建RedisTemplate对象目的是修改默认的序列化策略
    // RedisConnectionFactory 是springdatarediis封装的redis连接池工厂
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //修改key的序列化策略为普通字符串序列化，这样就不会进行jdk序列化了
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        //修改默认value的jdk序列器修改为json序列化器,效果是存储到redis服务器中是json字符串格式
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
        //注意：一般都不会修改value序列化器，因为一般都不需要关系redis存储数据的格式。

        return redisTemplate;
    }
}
