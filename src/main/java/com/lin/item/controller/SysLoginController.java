package com.lin.item.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.lin.item.common.config.RedisConfig;
import com.lin.item.common.constant.PromptConstant;
import com.lin.item.common.entity.Result;
import com.lin.item.common.service.TokenService;
import com.lin.item.common.util.SecurityUtil;
import com.lin.item.entity.SysUser;
import com.lin.item.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: L
 * @Date: 2023/3/8 12:15
 * @Desc: 登录、退出登录 - controller
 */
@RestController
@RequestMapping("/system")
public class SysLoginController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result Login(@RequestBody SysUser sysUser) {
        // 用户登录
        SysUser user = sysUserService.login(sysUser.getSysUserLogin(), sysUser.getSysUserPwd());
        // 返回TOKEN
        return TokenService.createToken(user);
    }

    /**
     * 退出登录
     * @return
     */
    @SaCheckLogin
    @PostMapping("/logout")
    public Result logout() {
        StpUtil.logout(SecurityUtil.getSysUserId());
        return Result.success(PromptConstant.LOGIN_OUT);
    }
}
