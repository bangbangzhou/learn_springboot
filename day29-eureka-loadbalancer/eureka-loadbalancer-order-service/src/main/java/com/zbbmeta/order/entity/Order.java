package com.zbbmeta.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Data
public class Order {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户主键ID
     */
    private Long userId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 用户信息
     */
    @TableField(exist = false)
    private User user;
}
