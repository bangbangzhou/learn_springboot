package com.zbbmeta.seatastudy.controller;


import com.zbbmeta.seatastudy.entity.Order;
import com.zbbmeta.seatastudy.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Long> createOrder(Order order){
        Long orderId = orderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GetMapping("/{id}")
    public void testOrder(@PathVariable("id") Long id){
        System.out.println("id: "+id);
    }


    @GetMapping("/query")
    public String queryOrder() {
        orderService.queryGoods();;
        return "查询order成功";
    }

    @GetMapping("/create")
    public String createOrder() {
        // 查询商品
        orderService.queryGoods();
        // 查询订单
        System.out.println("新增订单");
        return "新增order成功";
    }


    @GetMapping("/update")
    public String updateOrder() {
        return "更新order成功";
    }
}
