package com.zbs.mq;

import com.alibaba.fastjson.JSON;
import com.zbs.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * description: SmsService
 * date: 2023/4/10 15:52
 * author: zhangbs
 * version: 1.0
 */
@Slf4j
@Service
// consumerGroup：消费者组名 topic-要消费的主题
@RocketMQMessageListener(consumerGroup = "shop-order",topic = "order-topic")
public class SmsService implements RocketMQListener<Order> {
    // 消费逻辑
    @Override
    public void onMessage(Order order) {
        log.info("收到一个订单信息{},接下来发送短信", JSON.toJSONString(order));
    }
}
