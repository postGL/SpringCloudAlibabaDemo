package com.zbs.predicates;

import com.alibaba.nacos.common.utils.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * description: AgeRoutePredicateFactory
 * date: 2023/4/3 14:41
 * author: zhangbs
 * version: 1.0
 */
// 自定义路由断言工厂类，要求有两个：（必须交给spring管理，否则会找不到这个自定义断言）
//1、名字必须是  配置+RoutePredicateFactory
//2、必须继承AbstractRoutePredicateFactory<配置类>
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    // 构造函数
    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    // 读取配置文件中的参数值，给他赋值到配置类的属性上
    @Override
    public List<String> shortcutFieldOrder() {
        // 这个位置的顺序必须和配置文件中的值的顺序对应
        return Arrays.asList("minAge", "maxAge");
    }

    // 断言逻辑
    @Override
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        Predicate<ServerWebExchange> predicate = new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 接收前台传入的age参数，也可以从session中获取
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");

                // 1、先判断是否为空
                if (StringUtils.isNotBlank(ageStr)) {
                    // 2、如果不为空，再由路由逻辑判断
                    int age = Integer.parseInt(ageStr);
                    return age > config.minAge && age < config.maxAge;
                }
                return false;
            }
        };
        return predicate;
    }

    /**
     * 配置类用于接收配置文件中的对应参数
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private int minAge; //18
        private int maxAge; //60
    }
}
