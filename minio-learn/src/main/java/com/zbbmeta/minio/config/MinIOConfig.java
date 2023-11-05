package com.zbbmeta.minio.config;

import com.zbbmeta.minio.property.MinIOProperties;
import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Configuration
@EnableConfigurationProperties(MinIOProperties.class)
public class MinIOConfig {

    @Bean
    public MinioClient minioClient(MinIOProperties minIOProperties){
        MinioClient minioClient = MinioClient.builder()
                        .endpoint(minIOProperties.getEndpoint())
                        .credentials(minIOProperties.getAccessKey(), minIOProperties.getSecretKey())
                        .build();
        return minioClient;
    }
}