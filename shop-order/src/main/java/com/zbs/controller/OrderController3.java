package com.zbs.controller;

import com.alibaba.fastjson.JSON;
import com.zbs.domain.Order;
import com.zbs.domain.Product;
import com.zbs.service.Impl.OrderServiceImpl3;
import com.zbs.service.ProductService;
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
public class OrderController3 {

    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;

    @Autowired
    private ProductService productService;

    /**
     * 下单
     * MQ 事务消息
     * @param pid
     * @return
     */
    @RequestMapping("order/tx/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        // 调用商品微服务
        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品信息是：{}", pid, JSON.toJSONString(product));

        // 下单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderServiceImpl3.createOrderBefore(order);
        log.info("创建一个订单成功，订单是：{}", JSON.toJSONString(order));
        return order;
    }

}
