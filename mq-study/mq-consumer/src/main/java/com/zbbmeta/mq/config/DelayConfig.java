package com.zbbmeta.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Configuration
public class DelayConfig {

    //1.声明delay交换机
    @Bean
    public DirectExchange delayedExchange(){
        return ExchangeBuilder
                .directExchange("delay.direct") //指定交换机类型和名称
                .delayed() //设置delay属性为true,delayed()默认就设置delay属性为true
                .durable(true)
                .build();
    }
    //2.声明队列
    @Bean
    public Queue delayedQueue(){
        return new Queue("delay.queue");
    }
    //3.队列和交换机绑定
    @Bean
    public Binding delayedBinding(){
        return BindingBuilder.bind(delayedQueue())
                             .to(delayedExchange())
                            .with("delay");
    }
}
