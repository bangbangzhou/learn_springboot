package com.zbbmeta.seatastudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@MapperScan("com.zbbmeta.seatastudy.mapper")
@EnableFeignClients
@SpringBootApplication
public class SentinelOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelOrderApplication.class,args);
    }
}
