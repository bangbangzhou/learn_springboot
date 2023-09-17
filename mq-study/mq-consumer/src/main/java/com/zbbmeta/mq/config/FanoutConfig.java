package com.zbbmeta.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Configuration
public class FanoutConfig {

    /**
     * 声明交换机zbbmeta.fanout
     * @return Fanout类型交换机
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("zbbmeta.fanout");
    }

    /**
     * 声明交换机zbbmeta.fanout2
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange2(){
        return new FanoutExchange("zbbmeta.fanout2");
    }

    /**
     *  声明队列zbbmeta.fanout.queue1
     */
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("zbbmeta.fanout.queue1");
    }

    /**
     * 声明队列zbbmeta.fanout.queue2
     */
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("zbbmeta.fanout.queue2");
    }


    /**
     * 声明绑定队列zbbmeta.fanout.queue1和交换机zbbmeta.fanout绑定
     */
    @Bean
    public Binding bindingQueue1ToFanoutExchange(Queue fanoutQueue1, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue1) //绑定队列
                .to(fanoutExchange);//交换机
    }


    /**
     * 声明绑定队列zbbmeta.fanout.queue2和交换机zbbmeta.fanout绑定
     */
    @Bean
    public Binding bindingQueue2ToFanoutExchange(Queue fanoutQueue2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }


    /**
     * 声明绑定队列zbbmeta.fanout.queue2和交换机zbbmeta.fanout2绑定
     */
    @Bean
    public Binding bindingQueueToExchange2(Queue fanoutQueue2, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange2());
    }
}
