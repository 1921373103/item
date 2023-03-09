package com.lin.item.common.util;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import com.lin.item.common.constant.SystemConstant;
import com.lin.item.common.constant.TokenConstant;
import com.lin.item.entity.SysUser;

/**
 * @Author: L
 * @Date: 2023/3/8 12:29
 * @Desc:
 */
public class SecurityUtil {

    /**
     * 使用session获取用户登录名
     * @return
     */
    public static String getSysUserLogin() {
        SysUser user = (SysUser) StpUtil.getSession().get(TokenConstant.SESSION);
        return user.getSysUserLogin();
    }

    /**
     * 生成MD5加盐密码
     */
    public static String enMd5PassWord(String password) {
        return SecureUtil.md5(password + SystemConstant.SALT);
    }

    /**
     * 获取用户当前登陆人的id
     * @return
     */
    public static String getSysUserId() {
        return (String) StpUtil.getLoginId();
    }
}
