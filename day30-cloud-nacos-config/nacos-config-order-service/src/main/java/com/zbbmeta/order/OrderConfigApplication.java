package com.zbbmeta.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.zbbmeta.order.mapper")
public class OrderConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderConfigApplication.class,args);
    }
}
