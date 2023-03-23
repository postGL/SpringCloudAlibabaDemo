package com.zbs.common.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * description: BlockHandlerException
 * date: 2023/3/23 20:30
 * author: zhangbs
 * version: 1.0
 */
@Slf4j
public class BlockHandlerException {

    //注意这里必须使用static修饰方法

    /**
     * blockHandler
     * 要求：
     * 1、当前方法的返回值和参数要与原方法（加@SentinelResource注解的方法）一致
     * 2、但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
     */
    public static String blockHandler(String name, Integer age, BlockException e) {
        // 自定义异常逻辑处理
        log.error("触发了BlockException，内容为：{}", e);
        return "BlockException!";
    }

}
