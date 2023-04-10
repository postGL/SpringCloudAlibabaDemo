package com.zbs.test;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * description: RocketMQSendMessage
 * date: 2023/4/10 11:59
 * author: zhangbs
 * version: 1.0
 */
public class RocketMQSendMessage {

    // 发送消息
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        // 1、创建消息生产者，并且设置生产组名
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        // 2、为生产者设置NameServer的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 3、启动生产者
        producer.start();
        // 4、构建消息对象，主要设置消息的主题、标签、内容
        Message message = new Message("myTopic", "myTag", "RecoketMQ Message".getBytes());
        // 5、发送消息
        SendResult sendResult = producer.send(message, 10000);
        System.out.println(sendResult);
        // 6、关闭生产者
        producer.shutdown();

    }

}
