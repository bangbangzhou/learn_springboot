package com.zbbmeta.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Slf4j
@Configuration
public class ConfirmCallbackConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //从容器中获取RabbitTemplate bean对象
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            /**
             *    //消息从生产者Publisher(生产者)---->Exchange(交换机)
             * @param correlationData correlationData 相关配置信息
             * @param ack exchange交换机 是否成功收到了消息。true 成功，false代表失败
             * @param cause cause 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack){
                    //Exchange(交换机)接收成功
                    System.out.println("全局ConfirmCallback接收成功");
                }else {
                    //Exchange(交换机)接收失败
                    System.err.println("全局ConfirmCallback消息接收失败: "+cause);
                }
            }
        });
        // 设置ReturnCallback
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                // 投递失败，记录日志
                System.err.println("消息发送失败，应答码: "+   returned.getReplyCode()+ ", 原因:  "+ returned.getReplyText()+ ", 交换机: "+returned.getExchange()+
                        ", 路由键: "+returned.getRoutingKey()+ ", 消息: "+ returned.getMessage().toString()
                );
                // 如果有业务需要，可以重发消息
            }
        });

    }
}
