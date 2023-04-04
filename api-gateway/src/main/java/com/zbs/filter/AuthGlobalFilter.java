package com.zbs.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * description: AuthGlobalFilter
 * date: 2023/4/4 10:51
 * author: zhangbs
 * version: 1.0
 */
// 自定义全局过滤器（作用：统一鉴权）
// 要求：实现两个接口：GlobalFilter，实现两个方法：Ordered。
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    // 过滤器逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 统一鉴权（逻辑暂时注释）
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
//        if (!StringUtils.equals("admin", token)) {
//            // 认证失败
//            log.info("认证失败了！！！");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
        return chain.filter(exchange);
    }

    // 标识当前过滤器优先级，返回值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
