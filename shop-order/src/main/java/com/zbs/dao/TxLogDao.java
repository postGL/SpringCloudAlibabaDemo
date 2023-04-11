package com.zbs.dao;

import com.zbs.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description: OrderController
 * date: 2023/3/7 20:34
 * author: zhangbs
 * version: 1.0
 * JpaRepository<T, ID> T：实体；ID：主键类型
 */
public interface TxLogDao extends JpaRepository<TxLog, String> {

}

