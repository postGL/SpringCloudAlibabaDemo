package com.zbs.common.hadler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义返回异常页面
 * description: ExceptionHandlerPage
 * date: 2023/3/23 15:47
 * author: zhangbs
 * version: 1.0
 */
@Component
public class ExceptionHandlerPage implements BlockExceptionHandler {

    /**
     * Sentinel 降级、熔断异常信息页面自定义
     * @param request
     * @param response
     * @param e
     * @throws Exception
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        //BlockException 异常接口,包含Sentinel的五个异常
        // FlowException 限流异常
        // DegradeException 降级异常
        // ParamFlowException 参数限流异常
        // AuthorityException 授权异常
        // SystemBlockException 系统负载异常
        response.setContentType("application/json;charset=utf-8");
        ResponseData data = null;
        if (e instanceof FlowException) {
            data = new ResponseData(-1, "接口被限流了...");
        } else if (e instanceof DegradeException) {
            data = new ResponseData(-2, "接口被降级了...");
        }else if (e instanceof ParamFlowException){
            data = new ResponseData(-3, "接口被参数限流了...");
        }else if (e instanceof AuthorityException){
            data = new ResponseData(-4, "接口被授权限流了...");
        }

        response.getWriter().write(JSON.toJSONString(data));
    }
}
