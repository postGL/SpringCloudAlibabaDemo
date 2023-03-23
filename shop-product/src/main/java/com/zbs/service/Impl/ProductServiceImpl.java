package com.zbs.service.Impl;

import com.zbs.dao.ProductDao;
import com.zbs.domain.Product;
import com.zbs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: ProductServiceImpl
 * date: 2023/3/7 20:33
 * author: zhangbs
 * version: 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer pId) {
        return productDao.findById(pId).orElse(null);
    }
}
