package com.lin.item.controller;

import com.lin.item.common.entity.Result;
import com.lin.item.service.ISysFileService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 获取所有图片
     */
    @GetMapping("/list")
    public Result list() {
        return Result.success(iSysFileService.list());
    }

    /**
     * 上传图片
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        return Result.success(iSysFileService.upload(file));
    }

    /**
     * 删除图片
     */
    @DeleteMapping("/{fileId}")
    public Result removeById(@PathVariable Integer fileId) {
        return Result.toAjax(iSysFileService.removeById(fileId));
    }
}
