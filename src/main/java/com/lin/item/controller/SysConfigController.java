package com.lin.item.controller;

import com.lin.item.common.entity.Result;
import com.lin.item.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: L
 * @Date: 2023/3/9 15:30
 * @Desc: 参数配置
 */
@RestController
@RequestMapping("/config")
public class SysConfigController {

    @Autowired
    public ISysConfigService iSysConfigService;

    /**
     * 获取微信支付金额
     */
    @GetMapping("/WechatPrice")
    public Result getWechatPrice() {
        return Result.success(iSysConfigService.getWechatPrice());
    }

    /**
     * 修改微信支付金额
     */
    @PutMapping("/WechatPrice")
    public Result editWechatPrice(String configValue) {
        return Result.toAjax(iSysConfigService.editWechatPrice(configValue));
    }


    /**
     * 获取跳转链接
     */
    @GetMapping("/LinkUrl")
    public Result getLinkUrl() {
        return Result.success(iSysConfigService.getLinkUrl());
    }

    /**
     * 修改跳转链接
     */
    @PutMapping("/LinkUrl")
    public Result editLinkUrl(String configValue) {
        return Result.toAjax(iSysConfigService.editLinkUrl(configValue));
    }


}
