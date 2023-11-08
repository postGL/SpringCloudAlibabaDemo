package com.zbs.rpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: RemoteProductServiceImpl
 * date: 2023/11/8 17:44
 * author: zhangbs
 * version: 1.0
 */
// @DubboService可以支持dubbo调用方式，@Reference引入
@DubboService(version = "1.0")
// @RestController相当于继承RemoteProductService @RequestMapping("/product/reduceInventory")重新封装一个Rest controller
@RestController
public class RemoteProductServiceImpl implements RemoteProductService {

    @Override
    public void reduceInventory(Integer pid, int num) {

    }
}
