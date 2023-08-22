package com.zbbmeta;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class Day24SpringbootAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Day24SpringbootAdminServerApplication.class, args);
    }
}
