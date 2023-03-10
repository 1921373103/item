package com.lin.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.item.common.entity.IPage;
import com.lin.item.entity.PhoneCard;
import com.lin.item.vo.PhoneCardVo;

/**
 * @Author: L
 * @Date: 2023/3/9 23:00
 * @Desc: 校园电话卡 - Service
 */
public interface IPhoneCardService extends IService<PhoneCard> {

    /**
     * 查询校园卡(管理员查所有) - 分页
     */
    IPage<PhoneCard> queryPage(PhoneCardVo phoneCardVo);

    /**
     * 新增校园电话卡登记
     * @param phoneCard 数据
     * @return jieguo
     */
    Boolean add(PhoneCard phoneCard);

    /**
     * 修改校园电话卡信息
     */
    Boolean edit(PhoneCard phoneCard);
}
