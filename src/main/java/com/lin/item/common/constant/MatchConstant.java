package com.lin.item.common.constant;

/**
 * @Author: L
 * @Date: 2023/3/7 21:44
 * @Desc: 正则常量
 */
public class MatchConstant {

    /**
     * 邮箱正则
     */
    public static final String EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

    /**
     * 手机号正则
     */
    public static final String PHONE = "^1[3456789]\\d{9}$";
}
