package com.lin.item.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.lin.item.common.entity.Result;
import com.lin.item.common.util.AddressUtils;
import com.lin.item.common.util.IpUtils;
import com.lin.item.common.util.WechatPayUtil;
import com.lin.item.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: L
 * @Date: 2023/4/7 16:27
 * @Desc: 微信支付Controller
 */
@RestController
@RequestMapping("/wechat")
public class PayController {

    @Autowired
    private IPayService ipayService;

    @Autowired
    public WechatPayUtil wechatPayUtil;

    /**
     * 预下单
     */
    @PostMapping("/pay")
    public Result order(@RequestBody JSONObject jsonObject) {
        return Result.success(ipayService.orderPay(jsonObject));
    }

    /**
     * 查询订单接口
     */
    @GetMapping("/{outTradeNo}")
    public Map<String, String> getOrderByoutTradeNo(@PathVariable String outTradeNo) {
        return ipayService.getOrderByoutTradeNo(outTradeNo);
    }

    @GetMapping("/ip")
    public String ip(HttpServletRequest request) {
        // String remoteAddr = request.getRemoteAddr();
        return IpUtils.getIpAddr(request);
    }
    /**
     * 微信支付异步通知
     *
     * @param request
     * @return
     */
    @PostMapping("/wechatPayNotify")
    public Map<String, String> wechatPayNotify(HttpServletRequest request) {
        return ipayService.wechatPayNotify(request);
    }

}
