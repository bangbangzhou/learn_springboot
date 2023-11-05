package com.zbbmeta.task.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Data
@ConfigurationProperties(prefix = "xxl.job")
public class XXLProperties {

    private String adminAddresses;
    private String accessToken;
    private String appname;
    private String address;
    private String ip;
    private int port;
    private String logPath;
    private int logRetentionDays;
}
