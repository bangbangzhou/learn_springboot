package com.zbbmeta.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@SpringBootApplication
@MapperScan("com.zbbmeta.order.mapper")
@EnableDiscoveryClient
public class OrderBalancerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderBalancerApplication.class,args);
    }
}
