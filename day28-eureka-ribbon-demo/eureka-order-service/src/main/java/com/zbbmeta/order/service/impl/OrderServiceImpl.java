package com.zbbmeta.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbbmeta.order.entity.Order;
import com.zbbmeta.order.entity.User;
import com.zbbmeta.order.mapper.OrderMapper;
import com.zbbmeta.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order getOrderById(Long id) {
        Order order = orderMapper.selectById(id);

        if(order==null){
            return null;
        }
        User user = queryUserInfoById(order.getUserId());
        order.setUser(user);
        return order;

    }

    /**
     * 根据用户ID查找用户信息
     * @param userId    用户ID
     * @return          用户信息
     */
    private User queryUserInfoById(Long userId) {
//        String url = "http://localhost:8088/user/" + userId;
        String url = "http://user-service/user/" + userId;
        return restTemplate.getForObject(url, User.class);
    }
}
