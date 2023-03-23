package com.zbs.common.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: RestTemplateConfiguration
 * date: 2023/3/16 18:33
 * author: zhangbs
 * version: 1.0
 */
@Configuration
public class RibbonRuleConfiguration {

    /**
     * 在这里定义Ribbon负载均衡的规则
     * @return
     */
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }

}
