package com.lin.item.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.item.common.exception.CustomException;
import com.lin.item.dao.SysConfigDao;
import com.lin.item.entity.SysConfig;
import com.lin.item.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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
    @Transactional(rollbackFor = CustomException.class)
    public String editWechatPrice(String configValue) {
        boolean wechatPrice = update(new UpdateWrapper<SysConfig>().lambda().set(SysConfig::getConfigValue, configValue).eq(SysConfig::getConfigKey, "wechat_price"));
        if (wechatPrice == Boolean.TRUE) {
            throw new CustomException("修改微信支付金额失败!");
        }
        return configValue;
    }

    /**
     * 获取跳转链接
     * @return 链接地址
     */
    @Override
    public String getLinkUrl(String configKey) {
        return getOne(new QueryWrapper<SysConfig>().lambda().eq(SysConfig::getConfigKey, configKey)).getConfigValue();
    }

    /**
     * 修改跳转链接
     * @param sysConfig 链接地址
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public Boolean editLinkUrl(SysConfig sysConfig) {
        sysConfig.setUpdateTime(DateTime.now());
        return updateById(sysConfig);
    }

    /**
     * 管理员获取所有链接
     * @return
     */
    @Override
    public List<SysConfig> getUrlAll() {
        return list(new QueryWrapper<SysConfig>().lambda().ne(SysConfig::getConfigId, 1));
    }

    /**
     * 新增
     * @param sysConfig
     * @return
     */
    @Override
    public Boolean addLinkUrl(SysConfig sysConfig) {
        sysConfig.setCreateTime(DateTime.now());
        return save(sysConfig);
    }
}
