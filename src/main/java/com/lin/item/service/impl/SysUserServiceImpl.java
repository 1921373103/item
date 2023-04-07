package com.lin.item.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.item.common.config.RedisConfig;
import com.lin.item.common.constant.PromptConstant;
import com.lin.item.common.constant.UserConstants;
import com.lin.item.common.entity.IPage;
import com.lin.item.common.enums.DataEnum;
import com.lin.item.common.exception.CustomException;
import com.lin.item.common.util.SecurityUtil;
import com.lin.item.dao.SysUserDao;
import com.lin.item.entity.PhoneCard;
import com.lin.item.entity.SysUser;
import com.lin.item.service.ISysUserService;
import com.lin.item.vo.SysUserVo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: L
 * @Date: 2023/3/8 12:09
 * @Desc: 系统人员 - IMPL
 */
@Service("sysUserServiceImpl")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {

    /** 系统人员 - DAO */
    @Autowired
    private SysUserDao sysUserDao;

    /** redis工具类 */
    @Autowired
    private RedisConfig redisConfig;

    /**
     * 系统用户登录
     * @param sysUserLogin 用户名
     * @param sysUserPwd 用户密码
     * @return 用户信息
     */
    @Override
    public SysUser login(String sysUserLogin, String sysUserPwd) {
        // 查询用户信息
        Map<String, Object> map = new HashMap<>(2);
        map.put("sys_user_login", sysUserLogin);
        // 密码加密
        map.put("sys_user_pwd", SecurityUtil.enMd5PassWord(sysUserPwd));

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        SysUser sysUser = getOne(queryWrapper.allEq(map));

        // 判断是否登录成功
        if (null == sysUser) {
            throw new CustomException(UserConstants.LONIN_ERROR_MSG);
        }
        return sysUser;
    }

    /**
     * 查询营销用户(带分页)
     */
    @Override
    public IPage<SysUser> queryPage(SysUserVo sysUserVo) {

        // 初始化page
        Page<SysUser> page = new Page<>(sysUserVo.getPageNum(), sysUserVo.getPageSize());

        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();

        // 根据用户名称查询
        if (StrUtil.isNotEmpty(sysUserVo.getSysUserName())) {
            wrapper.lambda().like(SysUser::getSysUserName, sysUserVo.getSysUserName());
        }

        // 查询用户创建时间区间
        if (null != (sysUserVo.getTime())) {
            wrapper.lambda().between(SysUser::getCreateTime, sysUserVo.getTime().getCreateTimeS(), sysUserVo.getTime().getCreateTimeE());
        }

        // 执行查询
        Page<SysUser> result = sysUserDao.selectPage(page, wrapper);

        result.getRecords().forEach(res -> {
            res.setSysUserPwd(null);
        });



        // 总数、结果
        return new IPage(result.getTotal(), result.getCurrent(), result.getRecords());
    }

    /**
     * 根据营销用户ID查询
     */
    @Override
    public SysUser getInfo(Integer sysUserId) {
        SysUser byId = getById(sysUserId);
        byId.setSysUserPwd(null);
        return byId;
    }

    /**
     * 新增营销账号
     * @param sysUser 数据
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public Boolean add(SysUser sysUser) {
        // 判断登录名是否重复
        if (0 < list(new QueryWrapper<SysUser>().lambda()
                .eq(SysUser::getSysUserLogin, sysUser.getSysUserLogin())
                .eq(SysUser::getIsDel, DataEnum.USING.getCode())).size()) {
            throw new CustomException("登录名已存在!");
        }
        // 判断用户名是否重复
        if (0 < list(new QueryWrapper<SysUser>().lambda()
                .eq(SysUser::getSysUserName, sysUser.getSysUserName())
                .eq(SysUser::getIsDel, DataEnum.USING.getCode())).size()){
            throw new CustomException("用户名已存在!");
        }
        // 密码加密
        sysUser.setSysUserPwd(SecurityUtil.enMd5PassWord(sysUser.getSysUserPwd()));
        sysUser.setCreateBy(SecurityUtil.getSysUserLogin());
        sysUser.setCreateTime(DateUtil.date());
        return save(sysUser);
    }

    /**
     * 修改营销账号
     * @param sysUser 数据
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public Boolean edit(SysUser sysUser) {
        // 判断登录名是否修改
        if (!SecurityUtil.getSysUserLogin().equals(sysUser.getSysUserLogin())) {
            // 判断登录名是否重复
            if (0 < list(new QueryWrapper<SysUser>().lambda()
                    .eq(SysUser::getSysUserLogin, sysUser.getSysUserLogin())
                    .eq(SysUser::getIsDel, DataEnum.USING.getCode())).size()) {
                throw new CustomException("登录名已存在!");
            }
        }
        // 判断用户名是否修改
        if (!SecurityUtil.getSysUserName().equals(sysUser.getSysUserName())) {
            // 判断用户名是否重复
            if (0 < list(new QueryWrapper<SysUser>().lambda()
                    .eq(SysUser::getSysUserName, sysUser.getSysUserName())
                    .eq(SysUser::getIsDel, DataEnum.USING.getCode())).size()){
                throw new CustomException("用户名已存在!");
            }
        }
        // 密码加密
        sysUser.setSysUserPwd(SecurityUtil.enMd5PassWord(sysUser.getSysUserPwd()));
        sysUser.setUpdateBy(SecurityUtil.getSysUserLogin());
        sysUser.setUpdateTime(DateUtil.date());
        return updateById(sysUser);
    }

    /**
     * 删除营销账号
     * @param sysUserId 营销账号ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public Boolean removeBySysUserId(Integer sysUserId) {
        if (1 == sysUserId) {
            throw new CustomException("不允许删除");
        }
        return removeById(sysUserId);
    }
}
