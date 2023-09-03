package com.zbbmeta.user.controller;

import com.zbbmeta.user.entity.User;
import com.zbbmeta.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@RequestMapping("/user")
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User test(@PathVariable("id") Long id){

        User user = userService.getById(id);

        return user;
    }
}
