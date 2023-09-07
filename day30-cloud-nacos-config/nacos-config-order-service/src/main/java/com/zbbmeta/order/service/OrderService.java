package com.zbbmeta.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zbbmeta.order.entity.Order;


public interface OrderService extends IService<Order> {
    Order getOrderById(Long id);
}