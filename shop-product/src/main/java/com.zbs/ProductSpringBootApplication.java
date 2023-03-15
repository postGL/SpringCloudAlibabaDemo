package com.zbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * description: ProductSpringBootApplication
 * date: 2023/2/3 17:58
 * author: zhangbs
 * version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProductSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductSpringBootApplication.class, args);
    }
}
