package com.zbs.test;

import com.zbs.OrderSpringBootApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * description: MessageTypeTest
 * date: 2023/4/10 16:57
 * author: zhangbs
 * version: 1.0
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = OrderSpringBootApplication.class)
public class MessageTypeTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    //同步消息
    @Test
    public void testSyncSend() {
        // 参数一：订阅主题，如果需要添加tag标签，可以使用“topic:tag”的写法
        // 参数二：消息内容
        // 参数三：超时时间
        SendResult sendResult = rocketMQTemplate.syncSend("test-topic-sync:tag", "这是一条同步消息", 10000L);
        System.out.println(sendResult);
    }

    //异步消息
    @Test
    public void testAsyncSend() throws InterruptedException {
        // 参数一：订阅主题，如果需要添加tag标签，可以使用“topic:tag”的写法
        // 参数二：消息内容
        // 参数三：超时时间
        rocketMQTemplate.asyncSend("test-topic-async:tag", "这是一条异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送消息成功，" + sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("发送消息失败，" + throwable.getMessage());
            }
        }, 10000L);
        System.out.println("=======================");
    }

    //单向消息
    @Test
    public void testOneWay() {
        for (int i = 0; i < 10; i++) {
            // sendOneWayOrderly：order保证在一个消息messageQueue中加入，顺序消费
            rocketMQTemplate.sendOneWayOrderly("test-topic-one-way", "这是一条单向消息","XXX");
        }
    }

}
