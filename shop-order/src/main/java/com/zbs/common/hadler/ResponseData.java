package com.zbs.common.hadler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: ResponseData
 * date: 2023/3/23 16:46
 * author: zhangbs
 * version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {

    private int code;
    private String message;

}
