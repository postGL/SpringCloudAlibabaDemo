package com.zbs.controller;

import com.zbs.domain.Order;
import com.zbs.service.Impl.OrderSeataServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: OrderController
 * date: 2023/3/7 20:32
 * author: zhangbs
 * version: 1.0
 */
@RestController
@Slf4j
public class OrderSeataController {

    @Autowired
    private OrderSeataServiceImpl orderSeataService;

    /**
     * 下单 Sentinel
     * @param pid
     * @return
     */
    @RequestMapping("order/seata/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        return orderSeataService.createOrder(pid);
    }

}
