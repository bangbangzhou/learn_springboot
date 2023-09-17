package com.zbbmeta.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Slf4j
@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @GetMapping("/{msg}")
    public String sendMsg(@PathVariable("msg") String msg){
        // 队列名称
        String queueName = "simple.queue";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, msg);
        return msg;
    }

    /**
     * WorkQueue队列测试
     * @param num 发送消息次数
     * @param msg 消息
     * @return
     */
    @GetMapping("/work/{num}/{msg}")
    public String sendWorkMsg(@PathVariable("num") Integer num,@PathVariable("msg") String msg)throws InterruptedException {
        // 队列名称
        String queueName = "simple.queue";
        // 发送消息
        for (int i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend(queueName, msg+"_"+i);
            System.out.println(msg+"_"+i);
            Thread.sleep(20);
        }
        return msg;
    }


    /**
     * 广播测试
     * @param fanout 交换机名称
     * @param msg 消息
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/{fanout}/{msg}")
    public String sendFanoutMsg(@PathVariable("fanout") String fanout,@PathVariable("msg") String msg)throws InterruptedException {
        // 交换机名称
        String exchangeName ="zbbmeta."+fanout;
        //将消息发送到交换机Exchange
        // 发送消息
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(exchangeName,"",msg+"_"+i);
            System.out.println(msg+"_"+i);
            Thread.sleep(20);
        }
        return msg;
    }


    /**
     * 路由测试
     * @param msg 消息
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/direct/{num}/{key}/{msg}")
    public String sendDirectMsg(@PathVariable("num") Integer num,@PathVariable("key") String key,@PathVariable("msg") String msg)throws InterruptedException {
        // 交换机名称
        String exchangeName ="zbbmeta.direct";
        //将消息发送到交换机Exchange
        // 发送消息
        for (int i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend(exchangeName,key,msg+"_"+key+"_"+i);
            System.out.println(msg+"_"+i);
            Thread.sleep(20);
        }
        return msg;
    }

    /**
     * Topic测试
     * @param msg 消息
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/topic/{num}/{key}/{msg}")
    public String sendTopicMsg(@PathVariable("num") Integer num,@PathVariable("key") String key,@PathVariable("msg") String msg)throws InterruptedException {
        // 交换机名称
        String exchangeName ="zbbmeta.topic";
        //将消息发送到交换机Exchange
        // 发送消息
        for (int i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend(exchangeName,key,msg+"_"+key+"_"+i);
            System.out.println(msg+"_"+i);
            Thread.sleep(20);
        }
        return msg;
    }

    @GetMapping("/map")
    public Map<String,Object> sendMapMsg() {
        // 准备消息
        Map<String,Object> msg = new HashMap<>();
        msg.put("name", "zhangsan");
        msg.put("age", 21);
        // 发送消息
        rabbitTemplate.convertAndSend("simple.queue", msg);
        return  msg;
    }

    /**
     *确认模式：
     * 步骤：
     *
     * 1. 确认模式开启：在application.yml配置spring.rabbitmq. publisher-confirm-type  开启publisher-confirms="true"
     *      simple： 同步等待confirm结果，直到超时
     *      correlated 异步回调 推荐方式
     * 2. 在rabbitTemplate定义ConfirmCallBack回调函数
     * @param direct Exchange交换机名称
     * @param key    routingKey 路由
     * @param msg    message 消息
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/{direct}/{key}/{msg}")
    public String sendMsgCorrelationData(@PathVariable("direct") String direct,@PathVariable("key") String key,@PathVariable("msg") String msg)throws InterruptedException {

        //消息从生产者Publisher(生产者)---->Exchange(交换机)
        // 2.全局唯一的消息ID，需要封装到CorrelationData中
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 3.添加callback
        correlationData.getFuture().addCallback(
                result -> {
                    if(result.isAck()){
                        // ack，消息成功
                        System.out.println("局部ConfirmCallback消息发送成功: ID: "+correlationData.getId());
                    }else{
                        // 3.2 nack，消息失败
                        System.err.println("局部ConfirmCallback消息发送失败, ID: "+correlationData.getId()+" 原因: "+ result.getReason());
                    }
                },
                ex -> log.error("局部ConfirmCallback消息发送异常, ID:{}, 原因{}",correlationData.getId(),ex.getMessage())
        );

        rabbitTemplate.convertAndSend("zbbmeta.direct."+direct,key,msg,correlationData);
        return msg;
    }


    /**
     *确认模式：
     * 步骤：
     *
     * 1. 确认模式开启：在application.yml配置spring.rabbitmq. publisher-confirm-type  开启publisher-confirms="true"
     *      simple： 同步等待confirm结果，直到超时
     *      correlated 异步回调 推荐方式
     * 2. 在rabbitTemplate定义ConfirmCallBack回调函数
     * @param direct Exchange交换机名称
     * @param key    routingKey 路由
     * @param msg    message 消息
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/total/{direct}/{key}/{msg}")
    public String sendMsgConfirmCallback(@PathVariable("direct") String direct,@PathVariable("key") String key,@PathVariable("msg") String msg)throws InterruptedException {
        rabbitTemplate.convertAndSend("zbbmeta.direct."+direct,key,msg);
        return msg;
    }


    /**
     * 回退模式： 当消息发送给Exchange后，Exchange路由到Queue失败是 才会执行 ReturnCallBack
     * 步骤：
     *  1.开启回退模式 在application.yml 配置  设置spring.rabbitmq. publisher-returns = true
     *  2. 设置ReturnCallBack
     *  3. 设置Exchange处理消息的模式：
     *      3.1. 如果消息没有路由到Queue，则丢弃消息（默认）
     *      3.1. 如果消息没有路由到Queue，返回给消息发送方ReturnCallBack
     *          在application.yml 设置spring.rabbitmq.template.mandatory=true
     *
     *
     * @param direct
     * @param key
     * @param msg
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/returncallback/{direct}/{key}/{msg}")
    public String sendMsgReturnCallback(@PathVariable("direct") String direct,@PathVariable("key") String key,@PathVariable("msg") String msg)throws InterruptedException {
        rabbitTemplate.convertAndSend("zbbmeta.direct."+direct,key,msg);
        return msg;
    }


    @GetMapping("/durable/{msg}")
    public String sendMsgDurableMessage(@PathVariable("msg") String msg) {

        //创建消息，将消息封装到Message
        Message message = MessageBuilder
                .withBody(msg.getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();
        rabbitTemplate.convertAndSend("zbbmeta.direct.confirms","task",message);
        return msg;
    }

    @GetMapping("/handle/{msg}")
    public String sendMsgHandleMessage(@PathVariable("msg") String msg) {

        rabbitTemplate.convertAndSend("simple.queue",msg);
        return msg;
    }

    @GetMapping("/manual/{msg}")
    public String sendMsgManualHandleMessage(@PathVariable("msg") String msg) {

        rabbitTemplate.convertAndSend("simple.queue2",msg);
        return msg;
    }
}
