package com.zbs.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * description: User 用户
 * date: 2023/2/3 17:39
 * author: zhangbs
 * version: 1.0
 */
@Entity
@Table(name = "shop_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;//主键
    private String username;//用户名
    private String password;//密码
    private String telephone;//手机号
}
