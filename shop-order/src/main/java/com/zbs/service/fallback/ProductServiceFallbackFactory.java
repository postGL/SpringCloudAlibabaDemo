package com.zbs.service.fallback;

import com.zbs.domain.Product;
import com.zbs.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description: ProductServiceFallbackFactory
 * date: 2023/3/30 15:05
 * author: zhangbs
 * version: 1.0
 */
// 这是一个容错类，要求实现一个FallbackFactory<要为哪个接口产生容错类>
@Slf4j
@Service
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    // Throwable feign在调用过程中产生的异常
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {
                log.error("容错异常信息：{}", throwable.getMessage(), throwable);
                Product product = new Product();
                product.setPid(-100);
                product.setPname("远程调用商品微服务出现了异常，进入了容错逻辑。");
                return product;
            }

            @Override
            public void reduceInventory(Integer pid, int number) {
                log.error("容错异常信息：{}", throwable.getMessage(), throwable);
            }
        };
    }

}
