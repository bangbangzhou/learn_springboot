package com.zbbmeta.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName UserConfigApplication
 * @Description TODO
 * @Author 周棒棒
 * @Date 2023/9/7 9:40
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.zbbmeta.user.mapper")
public class UserGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserGatewayApplication.class,args);
    }
}
