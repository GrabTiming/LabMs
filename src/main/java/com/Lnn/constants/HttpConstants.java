package com.Lnn.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpConstants {

    SYSTEM_ERROR("系统错误",420),
    NEED_LOGIN("请先登录",418);

    private String message;
    private Integer code;

}
