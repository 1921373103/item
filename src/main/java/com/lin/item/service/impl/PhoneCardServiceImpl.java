package com.lin.item.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.item.common.entity.IPage;
import com.lin.item.common.exception.CustomException;
import com.lin.item.common.util.SecurityUtil;
import com.lin.item.dao.PhoneCardDao;
import com.lin.item.dao.SysUserDao;
import com.lin.item.entity.PhoneCard;
import com.lin.item.entity.SysUser;
import com.lin.item.service.IPhoneCardService;
import com.lin.item.vo.PhoneCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: L
 * @Date: 2023/3/9 23:01
 * @Desc: 校园电话卡 - IMPL
 */
@Service("phoneCardServiceImpl")
public class PhoneCardServiceImpl extends ServiceImpl<PhoneCardDao, PhoneCard> implements IPhoneCardService {

    @Autowired
    private PhoneCardDao phoneCardDao;

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 查询校园卡(管理员查所有) - 分页
     */
    @Override
    public IPage<PhoneCard> queryPage(PhoneCardVo phoneCardVo) {

        // 初始化page
        Page<PhoneCard> page = new Page<>(phoneCardVo.getPageNum(), phoneCardVo.getPageSize());

        QueryWrapper<PhoneCard> wrapper = new QueryWrapper<PhoneCard>();
        if (!"1".equals(SecurityUtil.getSysUserId())) {
            wrapper.lambda().eq(PhoneCard::getCreateBy,SecurityUtil.getSysUserLogin());
        }

        // 根据电话号码模糊查询
        if (!StrUtil.isEmpty(phoneCardVo.getCampusTelephoneCard())){
            wrapper.lambda().like(PhoneCard::getCampusTelephoneCard, phoneCardVo.getCampusTelephoneCard());
        }
        // 查询用户创建时间区间
        if (null != (phoneCardVo.getTime())) {
            wrapper.lambda().between(PhoneCard::getCreateTime, phoneCardVo.getTime().getCreateTimeS(), phoneCardVo.getTime().getCreateTimeE());
        }

        // 执行查询
        Page<PhoneCard> result = phoneCardDao.selectPage(page, wrapper);

        // 总数、结果
        return new IPage(result.getTotal(), result.getCurrent(), result.getRecords());
    }

    /**
     * 新增校园电话卡登记
     * @param phoneCard 数据
     * @return jieguo
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public Boolean add(PhoneCard phoneCard) {
        SysUser sysUser = sysUserDao.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getSysUserName, phoneCard.getAfterCare()));
        if (null == sysUser) {
            throw new CustomException("售后人员不存在!");
        }
        // 0未交钱 1已交钱 2交钱未激活
        phoneCard.setCardStatus(0);
        phoneCard.setAfterCare(sysUser.getSysUserName());
        phoneCard.setCreateBy(sysUser.getSysUserLogin());
        phoneCard.setCreateTime(DateUtil.date());
        return save(phoneCard);
    }

    /**
     * 修改校园电话卡信息
     */
    @Override
    public Boolean edit(PhoneCard phoneCard) {
        phoneCard.setUpdateBy(SecurityUtil.getSysUserLogin());
        phoneCard.setUpdateTime(DateUtil.date());
        return updateById(phoneCard);
    }
}
