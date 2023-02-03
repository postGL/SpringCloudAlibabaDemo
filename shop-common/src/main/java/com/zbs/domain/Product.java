package com.zbs.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * description: Product 商品
 * date: 2023/2/3 17:47
 * author: zhangbs
 * version: 1.0
 */
@Entity(name = "shop_product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;//主键
    private String pname;//商品名称
    private Double pprice;//商品价格
    private Integer stock;//库存
}
