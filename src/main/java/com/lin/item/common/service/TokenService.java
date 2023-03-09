package com.lin.item.common.service;

import cn.dev33.satoken.stp.StpUtil;
import com.lin.item.common.constant.PromptConstant;
import com.lin.item.common.constant.TokenConstant;
import com.lin.item.common.entity.Result;
import com.lin.item.common.util.SecurityUtil;
import com.lin.item.entity.SysUser;

import java.util.HashMap;

/**
 * @Author: L
 * @Date: 2023/3/8 12:44
 * @Desc: 登录存储TOKEN
 */
public class TokenService {

    /**
     * 创建令牌 - ADMIN
     */
    public static Result createToken(SysUser sysUser) {
        // 判断是否登录，如果登录先注销之前的登录令牌再登录
        if (null != StpUtil.getTokenValueByLoginId(sysUser.getSysUserId())) {
            StpUtil.logout(sysUser.getSysUserId());
        }
        // 登录
        StpUtil.login(sysUser.getSysUserId());
        sysUser.setSysUserPwd(null);
        // session中传入对象便于使用常量
        StpUtil.getSession().set(TokenConstant.SESSION, sysUser);
        HashMap<Object, Object> result = new HashMap<>(1);
        // 获取token
        result.put(TokenConstant.TOKEN, StpUtil.getTokenValue());
        result.put(TokenConstant.TOKEN_TIMEOUT, StpUtil.getTokenTimeout());
        if (1 == sysUser.getSysUserId()) {
            result.put("roleId", 0);
        } else {
            result.put("roleId", 1);
        }
        return Result.success(PromptConstant.LOGIN_SUCCESS, result);
    }
}
