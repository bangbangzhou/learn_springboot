package com.zbbmeta.config;

import com.zbbmeta.aspect.LogAspect;
import com.zbbmeta.properties.LogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Configuration
@EnableConfigurationProperties({LogProperties.class})
@ConditionalOnProperty(prefix = "zbbmeta.log" ,value = "enabled" ,matchIfMissing = true)
public class LogAutoConfig {

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
