package com.lin.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.item.entity.SysUser;

/**
 * @Author: L
 * @Date: 2023/3/8 12:07
 * @Desc: 系统人员SERVICE
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 系统用户登录
     * @param sysUserLogin 用户名
     * @param sysUserPwd 用户密码
     * @return 用户信息
     */
    SysUser login(String sysUserLogin, String sysUserPwd);

    /**
     * 新增营销账号
     */
    Boolean add(SysUser sysUser);

    /**
     * 修改营销账号
     */
    Boolean edit(SysUser sysUser);

    /**
     * 删除营销账号
     */
    Boolean removeBySysUserId(Integer sysUserId);
}
