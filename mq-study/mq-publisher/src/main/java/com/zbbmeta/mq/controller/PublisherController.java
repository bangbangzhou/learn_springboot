package com.zbbmeta.mq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
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
}
