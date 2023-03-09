package com.lin.item.common.enums;

import lombok.Getter;

@Getter
public enum DataEnum {
    /**
     * 数据状态，在用
     */
    USING(0, "在用")
    /**
     * 数据状态，停用
     */
    , DEACTIVATE(1, "停用")
    ;

    private Integer code;

    private String msg;

    DataEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}