package com.lin.item.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.lin.item.common.entity.Result;
import com.lin.item.entity.SysConfig;
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
    @SaCheckLogin
    @PutMapping("/WechatPrice")
    public Result editWechatPrice(String configValue) {
        return Result.success(iSysConfigService.editWechatPrice(configValue));
    }

    /**
     * 获取所有链接
     * @return
     */
    @GetMapping("/urlAll")
    public Result getUrlAll() {
        return Result.success(iSysConfigService.getUrlAll());
    }

    /**
     * 新增
     */
    @SaCheckLogin
    @PostMapping("/url")
    public Result addLinkUrl(@RequestBody SysConfig sysConfig) {
        return Result.toAjax(iSysConfigService.addLinkUrl(sysConfig));
    }

    /**
     * 修改跳转链接
     */
    @SaCheckLogin
    @PutMapping("/LinkUrl")
    public Result editLinkUrl(@RequestBody SysConfig sysConfig) {
        return Result.toAjax(iSysConfigService.editLinkUrl(sysConfig));
    }

    /**
     * 删除
     */
    @SaCheckLogin
    @DeleteMapping("/{configId}")
    public Result removeConfigId(@PathVariable Integer configId) {
        return Result.toAjax(iSysConfigService.removeById(configId));
    }



}
