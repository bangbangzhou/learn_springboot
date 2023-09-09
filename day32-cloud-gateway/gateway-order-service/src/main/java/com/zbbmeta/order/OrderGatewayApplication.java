package com.zbbmeta.order;


import com.zbbmeta.clients.config.MyFeignConfiguration;
import com.zbbmeta.clients.fegin.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


//@EnableFeignClients(defaultConfiguration = MyFeignConfiguration.class,basePackages = "com.zbbmeta.clients")
@EnableFeignClients(defaultConfiguration = MyFeignConfiguration.class,clients = {UserClient.class})
//@EnableFeignClients
@SpringBootApplication
@MapperScan("com.zbbmeta.order.mapper")
public class OrderGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderGatewayApplication.class,args);
    }
}
