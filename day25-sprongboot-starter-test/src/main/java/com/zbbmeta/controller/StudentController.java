package com.zbbmeta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public String  getAll(){
        return "student log test";
    }
}