package com.zbbmeta.clients.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
public class MyFeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
