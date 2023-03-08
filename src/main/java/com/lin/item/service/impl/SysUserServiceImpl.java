package com.lin.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.item.common.config.RedisConfig;
import com.lin.item.common.constant.PromptConstant;
import com.lin.item.common.constant.UserConstants;
import com.lin.item.common.exception.CustomException;
import com.lin.item.common.util.SecurityUtil;
import com.lin.item.dao.SysUserDao;
import com.lin.item.entity.SysUser;
import com.lin.item.service.ISysUserService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
