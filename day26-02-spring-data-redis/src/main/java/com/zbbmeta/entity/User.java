package com.zbbmeta.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Data
public class User implements Serializable {

    private String name;
    private Integer age;
}