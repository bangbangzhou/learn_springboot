package com.zbbmeta.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Component
public class SpringRabbitListener {

    //监听队列
//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
//        System.out.println("spring 消费者接收到消息：【" + msg + "】");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("work消费者1接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("work消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "zbbmeta.fanout.queue1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("fanout消费者1接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "zbbmeta.fanout.queue2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("fanout消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }


    /**
     *  声明zbbmeta.direct.queue1 队列 和zbbmeta.direct交换机绑定,routingkey为"red", "orange"
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "zbbmeta.direct.queue1"),
            exchange = @Exchange(name = "zbbmeta.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "orange"}
    ))
    public void listenDirectQueue1(String msg) throws InterruptedException {

        System.out.println("direct 消费者1接收到direct.queue1的消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }

    /**
     *  声明zbbmeta.direct.queue2 队列 和zbbmeta.direct交换机绑定,routingkey为"red", "green"
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "zbbmeta.direct.queue2"),
            exchange = @Exchange(name = "zbbmeta.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "green"}
    ))
    public void listenDirectQueue2(String msg) throws InterruptedException {

        System.err.println("direct 消费者2........接收到direct.queue2消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }

    /**
     *  声明zbbmeta.topic.queue1 队列 和zbbmeta.topic交换机绑定,routingkey为"china.#"
     */

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "zbbmeta.topic.queue1"),
            exchange = @Exchange(name = "zbbmeta.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg) throws InterruptedException {

        System.out.println("topic 消费者1 接收到zbbmeta.topic.queue1的消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }
    /**
     *  声明zbbmeta.topic.queue2 队列 和zbbmeta.topic交换机绑定,routingkey为"#.news"
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "zbbmeta.topic.queue2"),
            exchange = @Exchange(name = "zbbmeta.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg) throws InterruptedException {
        System.err.println("topic 消费者2........接收到zbbmeta.topic.queue2消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }
}