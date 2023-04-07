package com.lin.item.service;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: L
 * @Date: 2023/4/7 17:24
 * @Desc: 微信支付 service
 */
public interface IPayService {

    /**
     * 预支付
     * @param jsonObject
     * @return
     */
    Map<String, String> orderPay(JSONObject jsonObject, String campusTelephoneCard);

    Map<String, String> wechatPayNotify(HttpServletRequest request);
}
