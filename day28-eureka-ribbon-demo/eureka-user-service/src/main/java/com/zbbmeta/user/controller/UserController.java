package com.zbbmeta.user.controller;

import com.zbbmeta.user.entity.User;
import com.zbbmeta.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@RequestMapping("/user")
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private String  port;
    @GetMapping("/{id}")
    public User test(@PathVariable("id") Long id){
        System.out.println("user port: "+ port);
        User user = userService.getById(id);

        return user;
    }
}
