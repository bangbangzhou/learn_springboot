package com.zbbmeta.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {

    private String dateformat;

    private String sharedValue;

    private String name;


}