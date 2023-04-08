package com.lin.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.item.entity.SysConfig;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: L
 * @Date: 2023/3/8 15:13
 * @Desc: 参数配置表 - service
 */
public interface ISysConfigService extends IService<SysConfig> {

    /**
     * 获取微信支付金额
     */
    BigDecimal getWechatPrice();

    /**
     * 修改微信支付金额
     * @param configValue 金额
     * @return 结果
     */
    String editWechatPrice(String configValue);

    /**
     * 获取跳转链接
     * @return 链接地址
     */
    String getLinkUrl(String configKey);

    /**
     * 修改跳转链接
     * @return 结果
     */
    Boolean editLinkUrl(SysConfig sysConfig);

    /**
     * 管理员获取所有链接
     * @return
     */
    List<SysConfig> getUrlAll();

    /**
     * 新增
     * @param sysConfig
     * @return
     */
    Boolean addLinkUrl(SysConfig sysConfig);
}
