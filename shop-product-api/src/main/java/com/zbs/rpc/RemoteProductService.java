package com.zbs.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * description: ProductService
 * date: 2023/3/16 21:00
 * author: zhangbs
 * version: 1.0
 */
// Product product = restTemplate.getForObject("http://service-product/product/" + pid, Product.class);
// value用于指定调用nacos下的哪个微服务
// fallback用于指定接口的容错类
// fallback 和 fallbackFactory用一个
@FeignClient(
        value = "service-product"//,
//        fallback = ProductServiceFallback.class,
//        fallbackFactory = ProductServiceFallbackFactory.class
)
public interface RemoteProductService {

    //减库存【feign调用，多个参数时，需要@RequestParam】
    @RequestMapping("/product/reduceInventory")
    void reduceInventory(@RequestParam("pid") Integer pid, @RequestParam("number") int number);

}
