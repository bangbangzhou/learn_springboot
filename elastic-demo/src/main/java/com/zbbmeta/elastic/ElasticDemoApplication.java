package com.zbbmeta.elastic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@MapperScan("com.zbbmeta.elastic.mapper")
@SpringBootApplication
public class ElasticDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticDemoApplication.class, args);
    }

}
