package com.zbbmeta.seatastudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@MapperScan("com.zbbmeta.seatastudy.mapper")
@SpringBootApplication
public class SentinelStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelStorageApplication.class,args);
    }
}
