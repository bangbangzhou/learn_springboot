package com.zbbmeta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("com.zbbmeta.mapper")
public class Day21SpringbootSsmpApplication  implements EnvironmentAware {

    static Environment environment;
    public static void main(String[] args)  {

        System.out.println("虚机： "+System.getProperty("server.port"));
        SpringApplication.run(Day21SpringbootSsmpApplication.class, args);
        //   同名的命令行参数覆盖虚拟机参数
        System.out.println("命令行： "+environment.getProperty("server.port"));
        String[] arg = new String[1];
        arg[0] = "--server.port=82";

        System.out.println("*****启动成功*****");
    }

    // 注入环境对象
    @Override
    public void setEnvironment(Environment environment) {
        Day21SpringbootSsmpApplication.environment = environment;
    }

}
