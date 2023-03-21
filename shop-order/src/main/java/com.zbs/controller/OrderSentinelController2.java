package com.zbs.controller;

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
        return "测试高并发";
    }

    @RequestMapping("test2/message2")
    public String message2() {
        return "测试高并发";
    }

}
