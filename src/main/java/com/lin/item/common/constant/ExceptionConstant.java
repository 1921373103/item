package com.lin.item.common.constant;

/**
 * @Author: L
 * @Date: 2023/3/7 21:43
 * @Desc: 异常常量
 */
public class ExceptionConstant {

    /**
     * 权限拦截异常提示信息
     */
    public static final String AUTH_EXCEPTION = "当前账号没有权限，请联系管理员！";


    /**
     * token异常提示信息
     */
    public static final String TOKEN_EXCEPTION = "登录状态过期，请重新登录！";


    /**
     * 系统bug导致的异常
     */
    public static final String SYSTEM_ERROR = "系统异常，操作失败，请联系管理员！";
}
