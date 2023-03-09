package com.lin.item.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.lin.item.common.entity.Result;
import com.lin.item.entity.SysUser;
import com.lin.item.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: L
 * @Date: 2023/3/9 14:41
 * @Desc: 营销用户 - Controller
 */
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询营销用户
     */
    @SaCheckLogin
    @GetMapping("/list")
    public Result list() {
        return Result.success(sysUserService.list());
    }

    /**
     * 新增营销用户
     */
    @SaCheckLogin
    @PostMapping
    public Result add(@RequestBody SysUser sysUser) {
        return Result.toAjax(sysUserService.add(sysUser));
    }

    /**
     * 修改营销用户
     */
    @SaCheckLogin
    @PutMapping
    public Result edit(@RequestBody SysUser sysUser) {
        return Result.toAjax(sysUserService.edit(sysUser));
    }

    /**
     * 删除营销用户
     */
    @SaCheckLogin
    @DeleteMapping("/{sysUserId}")
    public Result removeBySysUserId(@PathVariable Integer sysUserId) {
        return Result.toAjax(sysUserService.removeBySysUserId(sysUserId));
    }

}
