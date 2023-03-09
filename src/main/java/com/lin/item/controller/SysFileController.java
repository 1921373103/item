package com.lin.item.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.lin.item.common.entity.Result;
import com.lin.item.common.interfaces.SelectGroup;
import com.lin.item.service.ISysFileService;
import com.lin.item.vo.SysFileVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: L
 * @Date: 2023/3/9 18:28
 * @Desc: 图片附件 - Controller
 */
@RestController
@RequestMapping("/file")
public class SysFileController {

    @Autowired
    private ISysFileService iSysFileService;

    /**
     * 获取所有图片(带分页)
     */
    @SaCheckLogin
    @GetMapping("/list")
    public Result list(@Validated(SelectGroup.class) SysFileVo sysFileVo) {
        return Result.success(iSysFileService.queryPage(sysFileVo));
    }

    /**
     * 根据图片ID查询详情
     */
    @SaCheckLogin
    @GetMapping("/{fileId}")
    public Result getInfo(@PathVariable Integer fileId) {
        return Result.success(iSysFileService.getById(fileId));
    }

    /**
     * 上传图片
     */
    @SaCheckLogin
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        return Result.success(iSysFileService.upload(file));
    }

    /**
     * 删除图片
     */
    @SaCheckLogin
    @DeleteMapping("/{fileId}")
    public Result removeById(@PathVariable Integer fileId) {
        return Result.toAjax(iSysFileService.removeById(fileId));
    }
}
