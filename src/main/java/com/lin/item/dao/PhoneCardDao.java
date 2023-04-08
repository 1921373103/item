package com.lin.item.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.item.entity.PhoneCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: L
 * @Date: 2023/3/9 22:59
 * @Desc: 校园电话卡 - DAO
 */
@Mapper
public interface PhoneCardDao extends BaseMapper<PhoneCard> {

}
