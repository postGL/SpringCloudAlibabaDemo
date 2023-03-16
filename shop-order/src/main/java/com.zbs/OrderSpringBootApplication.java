package com.zbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * description: OrderSpringBootApplication
 * date: 2023/2/3 17:58
 * author: zhangbs
 * version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderSpringBootApplication.class, args);
    }

}
