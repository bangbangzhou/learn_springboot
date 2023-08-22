package com.zbbmeta.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@ConfigurationProperties("zbbmeta.log")
@Data
public class LogProperties implements Serializable {

    private Boolean enabled;
}
