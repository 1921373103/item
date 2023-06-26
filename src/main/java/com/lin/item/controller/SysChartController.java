package com.lin.item.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.lin.item.common.entity.Result;
import com.lin.item.entity.SysChart;
import com.lin.item.service.ISysChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: L
 * @Date: 2023/3/9 20:43
 * @Desc: 轮播图 - Controller
 */
@RestController
@RequestMapping("/chart")
public class SysChartController {

    @Autowired
    private ISysChartService iSysChartService;

    /**
     * 查询轮播图
     */
    @GetMapping("/list")
    public Result list() {
        return Result.success(iSysChartService.listAll());
    }

    /**
     * 替换
     */
    @SaCheckLogin
    @PostMapping
    public Result replace(@RequestBody List<SysChart> sysChart) {
        return Result.toAjax(iSysChartService.replace(sysChart));
    }

    /**
     * 删除轮播图
     */
    @SaCheckLogin
    @DeleteMapping("/{chartIds}")
    public Result removeByIds(@PathVariable Integer[] chartIds) {
        return Result.toAjax(iSysChartService.removeByIds(Arrays.asList(chartIds)));
    }

}
