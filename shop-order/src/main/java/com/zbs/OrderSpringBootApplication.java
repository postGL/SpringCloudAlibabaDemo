package com.zbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * description: OrderSpringBootApplication
 * date: 2023/2/3 17:58
 * author: zhangbs
 * version: 1.0
 */
@EnableFeignClients // 开启feign客户端
@EnableDiscoveryClient // 开启nacos客户端
@SpringBootApplication
public class OrderSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderSpringBootApplication.class, args);
    }

}
