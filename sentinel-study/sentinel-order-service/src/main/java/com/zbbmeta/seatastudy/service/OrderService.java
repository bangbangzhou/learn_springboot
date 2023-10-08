package com.zbbmeta.seatastudy.service;


import com.zbbmeta.seatastudy.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    Long create(Order order);

    void queryGoods();
}