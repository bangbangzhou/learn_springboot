package com.zbbmeta.seatastudy.service.impl;



import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zbbmeta.seatastudy.client.AccountClient;
import com.zbbmeta.seatastudy.client.StorageClient;
import com.zbbmeta.seatastudy.entity.Order;
import com.zbbmeta.seatastudy.mapper.OrderMapper;
import com.zbbmeta.seatastudy.service.OrderService;
import feign.FeignException;
//import io.seata.spring.annotation.GlobalTransactional;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final AccountClient accountClient;
    private final StorageClient storageClient;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(AccountClient accountClient, StorageClient storageClient, OrderMapper orderMapper) {
        this.accountClient = accountClient;
        this.storageClient = storageClient;
        this.orderMapper = orderMapper;
    }

    @Override
    @SentinelResource("goods")
    public void queryGoods(){
        System.out.println("查询商品");
    }


    @Override
    @GlobalTransactional //开启分布式事务
    //@Transactional
    public Long create(Order order) {
        // 创建订单
        orderMapper.insert(order);
        try {
            // 扣用户余额
            accountClient.deduct(order.getUserId(), order.getMoney());
            // 扣库存
            storageClient.deduct(order.getCommodityCode(), order.getCount());

        } catch (FeignException e) {
            log.error("下单失败，原因:{}", e.contentUTF8(), e);
            throw new RuntimeException(e.contentUTF8(), e);
        }
        return order.getId();
    }
}
