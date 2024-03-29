package com.zbs.controller;

import com.alibaba.fastjson.JSON;
import com.zbs.domain.Order;
import com.zbs.domain.Product;
import com.zbs.service.OrderService;
import com.zbs.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description: OrderController
 * date: 2023/3/7 20:32
 * author: zhangbs
 * version: 1.0
 */
@RestController
@Slf4j
public class OrderSentinelController1 {

    @Autowired
    private OrderService orderService;

    @Resource
    private ProductService productService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 下单 Sentinel
     * @param pid
     * @return
     */
    @RequestMapping("test/order/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求，接下来调用商品微服务查询此商品信息", pid);
        // 调用商品微服务
        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品信息是：{}", pid, JSON.toJSONString(product));
        // 模拟调用需要2s的时间
//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 下单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        // 为了不产生大量的垃圾数据，将保存数据的地方注释掉
        orderService.createOrder(order);
        log.info("创建一个订单成功，订单是：{}", JSON.toJSONString(order));

        // 下单成功后，将消息放到MQ中
        // 参数1：指定topic、参数2：指定消息体
        rocketMQTemplate.convertAndSend("order-topic", order);

        return order;
    }

    @RequestMapping("test/message")
    public String message() {
        return "测试高并发";
    }

}
