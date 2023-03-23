package com.zbs.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zbs.fallback.BlockHandlerException;
import com.zbs.fallback.FallbackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: OrderController
 * date: 2023/3/7 20:32
 * author: zhangbs
 * version: 1.0
 */
@RestController
@Slf4j
public class OrderSentinelController2 {

    @RequestMapping("test2/message1")
    public String message1() {
//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "测试Sentinel ONE";
    }

    @RequestMapping("test2/message2")
    public String message2() {
        return "测试Sentinel TWO";
    }

    /**
     * 定义一个资源
     * SentinelResource
     * 定义当资源内部发生异常的时候的处理逻辑
     * blockHandler 定义当资源内部发生BlockException应该进入方法{捕获的是Sentinel定义的异常}
     * fallback 定义当资源内部发生了Throwable应该进入的方法
     *
     * 避免代码臃肿，支持Class类
     * blockHandlerClass
     * fallbackClass
     */
    @RequestMapping("test2/message3")
    @SentinelResource(
            value = "message3",
            blockHandler = "blockHandler",
            fallback = "fallback",
            blockHandlerClass = BlockHandlerException.class,
            fallbackClass = FallbackException.class)
    public String message2(String name, Integer age) {
        return name + age;
    }

//    /**
//     * blockHandler
//     * 要求：
//     * 1、当前方法的返回值和参数要与原方法（加@SentinelResource注解的方法）一致
//     * 2、但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
//     */
//    public String blockHandler(String name, Integer age, BlockException e) {
//        // 自定义异常逻辑处理
//        log.error("触发了BlockException，内容为：{}", e);
//        return "BlockException!";
//    }

//    /**
//     * fallback（如果blockHandler没有拦住的话，则进入fallback，fallback范围更大，一般只写一个fallback就OK）
//     * 要求：
//     * 1、当前方法的返回值和参数要与原方法（加@SentinelResource注解的方法）一致
//     * 2、但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
//     */
//    public String fallback(String name, Integer age, Throwable e) {
//        // 自定义异常逻辑处理
//        log.error("触发了Throwable，内容为：{}", e);
//        return "Throwable!";
//    }

}
