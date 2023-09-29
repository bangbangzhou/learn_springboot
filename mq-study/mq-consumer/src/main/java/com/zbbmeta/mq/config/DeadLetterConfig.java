//package com.zbbmeta.mq.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.http.PushBuilder;
//
///**
// * @author springboot葵花宝典
// * @description: TODO
// */
//@Configuration
//public class DeadLetterConfig {
//
//    //1.死信交换机deadL
//    @Bean
//    public DirectExchange dlExchange(){
//        return new DirectExchange("deadLetter.direct");
//    }
//    //2.声明私信队列
//    @Bean
//    public Queue dlQueue(){
//        return  new Queue("deadLetter.queue");
//    }
//    //3.死信队列和交换机绑定
//    @Bean
//    public Binding dlBinding(){
//        return BindingBuilder.bind(dlExchange())
//                        .to(dlExchange())
//                        .with("dlmail");
//    }
//
//    //4.声明接收注册信息的队列
//    @Bean
//    public Queue registerQueue(){
//        return QueueBuilder.durable("register.queue")
//                           .ttl(10*1000) //表示10s  可以自行练习模拟24小时
//                           .deadLetterExchange("deadLetter.direct") //指定死信交换机
//                            .deadLetterRoutingKey("dlmail") //指定死信交换机和队列之间的routingKey 路由
//                            .build();
//
//    }
//    //5.声明注册信息的交换机
//    @Bean
//    public DirectExchange registerDirectExchange(){
//        return new DirectExchange("register.direct");
//    }
//
//    //6.交换机和队列绑定
//    @Bean
//    public Binding registerBinding(){
//        return BindingBuilder.bind(registerQueue()).to(registerDirectExchange()).with("register");
//    }
//
//
//
//
//}
