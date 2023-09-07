package com.zbbmeta.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@SpringBootApplication
@MapperScan("com.zbbmeta.user.mapper")
public class UserBalancerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserBalancerApplication.class,args);
    }
}
