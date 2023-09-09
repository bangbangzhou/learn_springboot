package com.zbbmeta.clients.fegin;


import com.zbbmeta.clients.config.MyFeignConfiguration;
import com.zbbmeta.clients.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@FeignClient(value = "user-service",configuration = MyFeignConfiguration.class)
public interface UserClient {
    @GetMapping("/user/{id}")
    User test(@PathVariable("id") Long id);
}
/*
@FeignClient(value = "user-service")
public interface UserClient extends UserAPI {

}
*/
