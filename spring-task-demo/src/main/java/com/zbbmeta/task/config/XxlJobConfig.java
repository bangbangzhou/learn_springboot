package com.zbbmeta.task.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.zbbmeta.task.property.XXLProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Configuration
@Slf4j
@EnableConfigurationProperties(XXLProperties.class)
public class XxlJobConfig {



    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XXLProperties xxlProperties) {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlProperties.getAdminAddresses());
        xxlJobSpringExecutor.setAppname(xxlProperties.getAppname());
        xxlJobSpringExecutor.setAddress(xxlProperties.getAddress());
        xxlJobSpringExecutor.setIp(xxlProperties.getIp());
        xxlJobSpringExecutor.setPort(xxlProperties.getPort());
        xxlJobSpringExecutor.setAccessToken(xxlProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlProperties.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlProperties.getLogRetentionDays());

        return xxlJobSpringExecutor;
    }
}
