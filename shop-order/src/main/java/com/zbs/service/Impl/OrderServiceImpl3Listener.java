package com.zbs.service.Impl;

import com.zbs.dao.TxLogDao;
import com.zbs.domain.Order;
import com.zbs.domain.TxLog;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * description: OrderServiceImpl3Listener MQ事务回调监听
 * date: 2023/3/7 20:33
 * author: zhangbs
 * version: 1.0
 * @author zbs
 */
@RocketMQTransactionListener(txProducerGroup = "tx_producer_group")
@Service
public class OrderServiceImpl3Listener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;

    @Autowired
    private TxLogDao txLogDao;

    // 执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String txId = (String) msg.getHeaders().get("txId");
        try {
            // 本地事务
            Order order = (Order) arg;
            orderServiceImpl3.createOrder(txId, order);
            System.out.println("11111");
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }


    /**
     * 消息回查
     * 由于网络闪断、生产者应用重启等原因，导致某条事务消息的二次确认[4]丢失，
     * 某条消息长期处于“半事务消息”时,需要回查，否则不会调用回查。
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String txId = (String) msg.getHeaders().get("txId");
        TxLog txLog = txLogDao.getOne(txId);
        if (!ObjectUtils.isEmpty(txLog)) {
            // 有事务日志保存，本地事务成功了
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
