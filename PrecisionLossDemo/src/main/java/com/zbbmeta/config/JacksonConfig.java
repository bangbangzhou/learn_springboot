package com.zbbmeta.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

//*
// * @author springboot葵花宝典
// * @description: TODO

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule() {
            {
                addSerializer(Long.class, new ToStringSerializer());
                addDeserializer(Long.class, new JsonDeserializer<Long>() {
                    @Override
                    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                        return Long.parseLong(p.getText());
                    }
                });
            }
        });
        return objectMapper;
    }
}
