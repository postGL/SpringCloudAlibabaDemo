package com.zbs.service.Impl;

import com.zbs.dao.OrderDao;
import com.zbs.dao.TxLogDao;
import com.zbs.domain.Order;
import com.zbs.domain.TxLog;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * description: UserServiceImpl
 * date: 2023/3/7 20:33
 * author: zhangbs
 * version: 1.0
 * @author zbs
 */
@Service
public class OrderServiceImpl3 {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TxLogDao txLogDao;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void createOrderBefore(Order order) {
        String uuId = UUID.randomUUID().toString();

        // 发送半事务消息
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_group",
                "tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId", uuId).build(),
                order);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createOrder(String txId, Order order) {
        // 保存订单
        orderDao.save(order);

        // 记录事务日志
        TxLog txLog = new TxLog();
        txLog.setTxId(txId)
                .setContent("发送消息事务")
                .setDate(new Date());
        txLogDao.save(txLog);
    }

}
