package com.zbs.service.Impl;

import com.zbs.dao.OrderDao;
import com.zbs.domain.Order;
import com.zbs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: UserServiceImpl
 * date: 2023/3/7 20:33
 * author: zhangbs
 * version: 1.0
 * @author zbs
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order createOrder(Order order) {
        Order saveOrder = orderDao.save(order);
        return saveOrder;
    }
}
