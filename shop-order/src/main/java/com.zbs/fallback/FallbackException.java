package com.zbs.fallback;

import lombok.extern.slf4j.Slf4j;

/**
 * description: FallbackException
 * date: 2023/3/23 20:31
 * author: zhangbs
 * version: 1.0
 */
@Slf4j
public class FallbackException {

    /**
     * fallback（如果blockHandler没有拦住的话，则进入fallback，fallback范围更大，一般只写一个fallback就OK）
     * 要求：
     * 1、当前方法的返回值和参数要与原方法（加@SentinelResource注解的方法）一致
     * 2、但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
     */
    public static String fallback(String name, Integer age, Throwable e) {
        // 自定义异常逻辑处理
        log.error("触发了Throwable，内容为：{}", e);
        return "Throwable!";
    }

}
