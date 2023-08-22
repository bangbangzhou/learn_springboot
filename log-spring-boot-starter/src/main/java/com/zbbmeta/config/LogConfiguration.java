package com.zbbmeta.config;

import com.zbbmeta.aspect.LogAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@AutoConfiguration
public class LogConfiguration {

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
