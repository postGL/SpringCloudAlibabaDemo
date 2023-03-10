package com.zbs.controller;

import com.alibaba.fastjson.JSON;
import com.zbs.domain.Product;
import com.zbs.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: ProductController
 * date: 2023/3/7 20:32
 * author: zhangbs
 * version: 1.0
 */
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 通过Id查询商品
     * @param pId
     * @return
     */
    @RequestMapping("/product/{pId}")
    public Product getProductById(@PathVariable("pId") Integer pId) {
        log.info("查询{}号的订单商品", pId);
        Product product = productService.getProductById(pId);
        log.info("商品信息查询成功，商品为：{}", JSON.toJSONString(product));
        return product;
    }

}
