package com.lin.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.item.dao.SysConfigDao;
import com.lin.item.entity.SysConfig;
import com.lin.item.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author: L
 * @Date: 2023/3/9 15:42
 * @Desc:
 */
@Service("sysConfigServiceImpl")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements ISysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;

    /**
     * 获取微信支付金额
     */
    @Override
    public BigDecimal getWechatPrice() {
        return new BigDecimal(getOne(new QueryWrapper<SysConfig>().lambda().eq(SysConfig::getConfigKey, "wechat_price")).getConfigValue());
    }

    /**
     * 修改微信支付金额
     * @param configValue 金额
     * @return 结果
     */
    @Override
    public Boolean editWechatPrice(String configValue) {
        return update(new UpdateWrapper<SysConfig>().lambda().set(SysConfig::getConfigValue, configValue).eq(SysConfig::getConfigKey, "wechat_price"));
    }

    /**
     * 获取跳转链接
     * @return 链接地址
     */
    @Override
    public String getLinkUrl() {
        return getOne(new QueryWrapper<SysConfig>().lambda().eq(SysConfig::getConfigKey, "link_url")).getConfigValue();
    }

    /**
     * 修改跳转链接
     * @param configValue 链接地址
     * @return 结果
     */
    @Override
    public Boolean editLinkUrl(String configValue) {
        return update(new UpdateWrapper<SysConfig>().lambda().set(SysConfig::getConfigValue, configValue).eq(SysConfig::getConfigKey, "link_url"));
    }
}
