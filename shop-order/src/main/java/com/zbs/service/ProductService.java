package com.zbs.service;

import com.zbs.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description: ProductService
 * date: 2023/3/16 21:00
 * author: zhangbs
 * version: 1.0
 */
// Product product = restTemplate.getForObject("http://service-product/product/" + pid, Product.class);
@FeignClient(value = "service-product")
public interface ProductService {

    // 调Rest接口。@FeignClient+@GetMapping 就是一个完整的请求路径 http://serviceproduct/product/{pid}
    @RequestMapping("/product/{pid}")
    Product findByPid(@PathVariable Integer pid);

}
