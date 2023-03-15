package com.zbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * description: OrderSpringBootApplication
 * date: 2023/2/3 17:58
 * author: zhangbs
 * version: 1.0
 */
@SpringBootApplication
public class OrderSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderSpringBootApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
