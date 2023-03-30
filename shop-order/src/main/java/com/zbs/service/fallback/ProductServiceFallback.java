package com.zbs.service.fallback;

import com.zbs.domain.Product;
import com.zbs.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * description: ProductServiceFallBack
 * date: 2023/3/30 14:41
 * author: zhangbs
 * version: 1.0
 */
// 这是一个容错类，容错类要求必须实现被容错的接口,并为每个方法实现容错方案
@Service
public class ProductServiceFallback implements ProductService {

    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.setPid(-100);
        product.setPname("远程调用商品微服务出现了异常，进入了容错逻辑。");
        return product;
    }
}
