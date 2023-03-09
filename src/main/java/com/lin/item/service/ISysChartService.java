package com.lin.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.item.dto.SysChartDto;
import com.lin.item.entity.SysChart;

import java.util.List;

/**
 * @Author: L
 * @Date: 2023/3/9 20:37
 * @Desc: 轮播图 - SERVICE
 */
public interface ISysChartService extends IService<SysChart> {

    /**
     * 查询正在使用的轮播图
     */
    List<SysChartDto> listAll();

    /**
     * 替换
     * @param sysChart 图片
     * @return 结果
     */
    Boolean replace(List<SysChart> sysChart);
}
