package com.zbbmeta.order.entity;


import lombok.Data;

@Data
public class User {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户地址
     */
    private String address;
}