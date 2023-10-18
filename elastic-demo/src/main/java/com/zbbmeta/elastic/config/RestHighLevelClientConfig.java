package com.zbbmeta.elastic.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Configuration
public class RestHighLevelClientConfig {


    @Bean
    public RestHighLevelClient restHighLevelClient(){
        // 配置用户名和密码
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "123456"));

        // 创建 RestHighLevelClient
       return new RestHighLevelClient(
                RestClient.builder( HttpHost.create("http://192.168.150.102:9200"))
                        .setHttpClientConfigCallback(httpClientBuilder ->
                                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
        );

    }
}
