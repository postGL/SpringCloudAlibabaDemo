package com.zbs.service.Impl;

import com.alibaba.fastjson.JSON;
import com.zbs.dao.OrderDao;
import com.zbs.domain.Order;
import com.zbs.domain.Product;
import com.zbs.rpc.RemoteProductService;
import com.zbs.service.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * description: UserServiceImpl
 * date: 2023/3/7 20:33
 * author: zhangbs
 * version: 1.0
 * @author zbs
 */
@Service
@Slf4j
public class OrderSeataServiceImpl {

    @Autowired
    private OrderDao orderDao;

    @Resource
    private ProductService productService;

    @Resource
    private RemoteProductService remoteProductService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GlobalTransactional // Seata全局事务控制
    public Order createOrder(Integer pid) {
        log.info("接收到{}号商品的下单请求，接下来调用商品微服务查询此商品信息", pid);
        // 1、调用商品微服务
        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品信息是：{}", pid, JSON.toJSONString(product));

        // 2、下单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建一个订单成功，订单是：{}", JSON.toJSONString(order));

        // 3、扣库存
        remoteProductService.reduceInventory(pid, order.getNumber());

        // 4、下单成功后，将消息放到MQ中
        // 参数1：指定topic、参数2：指定消息体
        rocketMQTemplate.convertAndSend("order-topic", order);
        return order;
    }
}
