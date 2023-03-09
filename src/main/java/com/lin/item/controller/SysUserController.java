package com.lin.item.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.lin.item.common.entity.Result;
import com.lin.item.common.interfaces.SelectGroup;
import com.lin.item.entity.SysUser;
import com.lin.item.service.ISysUserService;
import com.lin.item.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
     * 查询营销用户(带分页)
     */
    @SaCheckLogin
    @GetMapping("/list")
    public Result list(@Validated(SelectGroup.class) SysUserVo sysUserVo) {
        return Result.success(sysUserService.queryPage(sysUserVo));
    }

    /**
     * 根据营销用户ID查询
     */
    @SaCheckLogin
    @GetMapping("/{sysUserId}")
    public Result getInfo(@PathVariable Integer sysUserId) {
        return Result.success(sysUserService.getInfo(sysUserId));
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
