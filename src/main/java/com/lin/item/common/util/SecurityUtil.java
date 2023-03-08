package com.lin.item.common.util;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import com.lin.item.common.constant.SystemConstant;

/**
 * @Author: L
 * @Date: 2023/3/8 12:29
 * @Desc:
 */
public class SecurityUtil {


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
