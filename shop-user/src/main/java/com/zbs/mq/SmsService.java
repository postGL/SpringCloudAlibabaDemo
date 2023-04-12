package com.zbs.mq;

import com.alibaba.fastjson.JSON;
import com.zbs.dao.UserDao;
import com.zbs.domain.Order;
import com.zbs.domain.User;
import com.zbs.util.SmsUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

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

    @Autowired
    private UserDao userDao;

    /**
     * 消息消费逻辑
     */
    @Override
    public void onMessage(Order order) {
        log.info("收到一个订单信息{},接下来发送短信", JSON.toJSONString(order));
        // 发送短信
        //根据uid 获取手机号
        User user = userDao.findById(order.getUid()).get();

        //生成验证码
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(new Random().nextInt(9) + 1);
        }
        String smsCode = builder.toString();
        Param param = new Param(smsCode);
        try {
            //发送短信 {"code":"123456"}
            SmsUtil.sendSms(user.getTelephone(), "SpriCloudAli", "SMS_276195466", JSON.toJSONString(param));
            log.info("短信发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Param {
        private String code;
    }

}
