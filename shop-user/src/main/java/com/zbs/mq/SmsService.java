package com.zbs.mq;

import com.alibaba.fastjson.JSON;
import com.zbs.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
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
// consumerGroup：消费者组名，
// topic-要消费的主题，
// consumeMode：是否顺序消费,CONCURRENTLY同步消费，默认；ORDERLY：顺序消费；
// messageModel：消息模式，广播模式 BROADCASTING，集群模式 默认，CLUSTERING
// 广播消费: 每个消费者实例都会收到消息,也就是一条消息可以被每个消费者实例处理；
// 集群消费: 一条消息只能被一个消费者实例消费
@RocketMQMessageListener(
        consumerGroup = "shop-order",
        topic = "order-topic",
        consumeMode = ConsumeMode.CONCURRENTLY,
        messageModel = MessageModel.CLUSTERING)
public class SmsService implements RocketMQListener<Order> {
    // 消费逻辑
    @Override
    public void onMessage(Order order) {
        log.info("收到一个订单信息{},接下来发送短信", JSON.toJSONString(order));
    }
}
