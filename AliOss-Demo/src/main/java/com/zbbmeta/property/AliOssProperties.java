package com.zbbmeta.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Data
@ConfigurationProperties(prefix = "alioss")
public class AliOssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;


}