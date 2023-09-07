package com.zbbmeta.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaBalancerApplication {

    public static void main(String[] args) {
       SpringApplication.run(EurekaBalancerApplication.class,args);
    }
}
