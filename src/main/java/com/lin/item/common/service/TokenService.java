package com.lin.item.common.service;

import cn.dev33.satoken.stp.StpUtil;
import com.lin.item.common.constant.PromptConstant;
import com.lin.item.common.constant.TokenConstant;
import com.lin.item.common.entity.Result;
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
        // 判断是否重复登录
        String token = StpUtil.getTokenValueByLoginId(sysUser.getSysUserId());
        // 登录
        StpUtil.login(sysUser.getSysUserId());
        sysUser.setSysUserPwd(null);
        // session中传入对象便于使用常量
        StpUtil.getSession().set(TokenConstant.SESSION, sysUser);
        HashMap<Object, Object> result = new HashMap<>(1);
        // 获取token
        if (null != token) {
            result.put(TokenConstant.TOKEN, token);
        } else {
            result.put(TokenConstant.TOKEN, StpUtil.getTokenValue());
        }
        result.put(TokenConstant.TOKEN_TIMEOUT, StpUtil.getTokenTimeout());
        return Result.success(PromptConstant.LOGIN_SUCCESS, result);
    }
}
