package com.zbbmeta.mq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author springboot葵花宝典
 * @description: 消息对接问题解决
 */
@Configuration
public class LazyConfig {


    //2.声明队列
    @Bean
    public Queue lazyQueue(){
        return QueueBuilder
                .durable("lazy1.queue")
                .lazy() //声明x-queue-mode属性为lazy,也就是惰性队列
                .build();
    }
}
