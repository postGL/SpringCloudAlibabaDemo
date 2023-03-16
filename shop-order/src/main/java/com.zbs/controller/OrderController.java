package com.zbs.controller;

import com.alibaba.fastjson.JSON;
import com.zbs.domain.Order;
import com.zbs.domain.Product;
import com.zbs.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 下单
     * 第三次：Ribbon负载均衡。
     * @param pid
     * @return
     */
    @RequestMapping("order/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求，接下来调用商品微服务查询此商品信息", pid);
        // 调用商品微服务
        String url = "service-product";

        Product product = restTemplate.getForObject("http://" + url + "/product/" + pid, Product.class);
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

//    /**
//     * 下单
//     * 第三次：自定义客户端随机选择服务。
//     * 商品服务是集群，通过客户端选择调用哪个服务。
//     * @param pid
//     * @return
//     */
//    @RequestMapping("order/{pid}")
//    public Order order(@PathVariable("pid") Integer pid) {
//        log.info("接收到{}号商品的下单请求，接下来调用商品微服务查询此商品信息", pid);
//        // 调用商品微服务
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        // 随机选择，获取随机数
//        int index = new Random().nextInt(instances.size());
//
//        ServiceInstance serviceInstance = instances.get(index);
//
//        Product product = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + pid, Product.class);
//        log.info("查询到{}号商品信息是：{}", pid, JSON.toJSONString(product));
//        // 下单
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        Order order1 = orderService.createOrder(order);
//        log.info("创建一个订单成功，订单是：{}", JSON.toJSONString(order1));
//        return order1;
//    }

//    /**
//     * 下单 第二次：通过Nacos注册中心获取商品微服务实例，获取实例信息（IP和端口）
//     * @param pid
//     * @return
//     */
//    @RequestMapping("order/{pid}")
//    public Order order(@PathVariable("pid") Integer pid) {
//        log.info("接收到{}号商品的下单请求，接下来调用商品微服务查询此商品信息", pid);
//        // 调用商品微服务
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        ServiceInstance serviceInstance = instances.get(0);
//
//        Product product = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + pid, Product.class);
//        log.info("查询到{}号商品信息是：{}", pid, JSON.toJSONString(product));
//        // 下单
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        Order order1 = orderService.createOrder(order);
//        log.info("创建一个订单成功，订单是：{}", JSON.toJSONString(order1));
//        return order1;
//    }

//    /**
//     * 下单 第一次：通过注入restTemplate，写死固定调用URL获取
//     * @param pid
//     * @return
//     */
//    @RequestMapping("order/{pid}")
//    public Order order(@PathVariable("pid") Integer pid) {
//        log.info("接收到{}号商品的下单请求，接下来调用商品微服务查询此商品信息", pid);
//        // 调用商品微服务
//        // 1、一旦服务提供者地址变化，就需要手工修改代码
//        // 2、一旦是多个服务提供者，无法实现负载均衡功能
//        // 3、一旦服务变得越来越多，人工维护调用关系困难
//        Product product = restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);
//        log.info("查询到{}号商品信息是：{}", pid, JSON.toJSONString(product));
//        // 下单
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        Order order1 = orderService.createOrder(order);
//        log.info("创建一个订单成功，订单是：{}", JSON.toJSONString(order1));
//        return order1;
//    }

}
