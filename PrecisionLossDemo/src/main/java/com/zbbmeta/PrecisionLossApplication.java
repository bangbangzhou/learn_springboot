package com.zbbmeta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("com.zbbmeta.mapper")
public class PrecisionLossApplication  {


    public static void main(String[] args)  {

        SpringApplication.run(PrecisionLossApplication.class, args);

    }

}
