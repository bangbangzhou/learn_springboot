package com.zbbmeta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zbbmeta.mapper")
public class Day21SpringbootSsmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Day21SpringbootSsmpApplication.class, args);
    }

}
