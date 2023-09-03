package com.zbbmeta.user.entity;

import lombok.Data;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
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
