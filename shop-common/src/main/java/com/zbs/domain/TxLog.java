package com.zbs.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * description: TxLog MQ消息事务状态记录表
 * date: 2023/4/11 11:22
 * author: zhangbs
 * version: 1.0
 */
@Entity
@Table(name = "shop_txlog")
@Accessors(chain = true)
@Data
public class TxLog {

    @Id
    private String txId;
    private String content;
    private Date date;

}
