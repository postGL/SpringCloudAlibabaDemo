package com.zbs.controller;

import com.alibaba.fastjson.JSON;
import com.zbs.domain.Order;
import com.zbs.domain.Product;
import com.zbs.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * description: OrderController
 * date: 2023/3/7 20:32
 * author: zhangbs
 * version: 1.0
 */
@RestController
@Slf4j
public class OrderController {

    // restTemplate调用远程服务
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    /**
     * 下单
     * @param pid
     * @return
     */
    @RequestMapping("order/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求，接下来调用商品微服务查询此商品信息", pid);
        // 调用商品微服务
        Product product = restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);
        log.info("查询到{}号商品信息是：{}", pid, JSON.toJSONString(product));
        // 下单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        Order order1 = orderService.createOrder(order);
        log.info("创建一个订单成功，订单是：{}", JSON.toJSONString(order1));
        return order1;
    }

}
