package com.zbbmeta.order.controller;

import com.zbbmeta.order.entity.Order;
import com.zbbmeta.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;



    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable("id") Long id){
        Order order = orderService.getOrderById(id);
        return order;
    }

}
