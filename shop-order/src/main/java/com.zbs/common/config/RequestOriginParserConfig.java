package com.zbs.common.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * description: RequestOriginParserDefinition
 * date: 2023/3/23 15:20
 * author: zhangbs
 * version: 1.0
 */
@Component
public class RequestOriginParserConfig implements RequestOriginParser {

    /**
     * Sentinel：授权规则
     * 区分来源：本质作用就是通过request域获取来源表数
     * 例如：app、pc
     * 然后交给流控应用进行匹配
     * @param request
     * @return
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String serviceName = request.getParameter("serviceName");
        if (ObjectUtils.isEmpty(serviceName)){
            throw new RuntimeException("serviceName is empty");
        }
        return serviceName;
    }
}
