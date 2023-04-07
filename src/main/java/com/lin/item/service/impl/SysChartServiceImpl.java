package com.lin.item.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.item.common.exception.CustomException;
import com.lin.item.common.util.SecurityUtil;
import com.lin.item.dao.SysChartDao;
import com.lin.item.dao.SysConfigDao;
import com.lin.item.dao.SysFileDao;
import com.lin.item.dto.SysChartDto;
import com.lin.item.entity.SysChart;
import com.lin.item.service.ISysChartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: L
 * @Date: 2023/3/9 20:40
 * @Desc: 轮播图 - IMPL
 */
@Service("sysChartServiceImpl")
public class SysChartServiceImpl extends ServiceImpl<SysChartDao, SysChart> implements ISysChartService {

    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private SysFileDao sysFileDao;

    /**
     * 查询正在使用的轮播图
     */
    @Override
    public List<SysChartDto> listAll() {
        List<SysChartDto> chartDtoList = new ArrayList<>();
        list().forEach(sysChart -> {
            SysChartDto sysChartDto = new SysChartDto();
            BeanUtil.copyProperties(sysChart, sysChartDto);
            chartDtoList.add(sysChartDto);
        });
        chartDtoList.forEach(chart -> {
            if (null != sysFileDao.selectById(chart.getFileId())) {
                chart.setFileUrl(sysFileDao.selectById(chart.getFileId()).getFileUrl());
            }
        });
        return chartDtoList;
    }

    /**
     * 替换
     * @param sysChart 图片
     * @return 结果
     */
    @Override
    public Boolean replace(List<SysChart> sysChart) {
        sysChart.forEach(chart -> {
            // 如果存在ID就修改 if (null != chart.getChartId()) { flag.set(updateById(chart)); } else { // 否则就新增flag.set(save(chart)); }
            chart.setCreateBy(SecurityUtil.getSysUserLogin());
            chart.setCreateTime(DateUtil.date());
            if (!saveOrUpdate(chart)) {
                throw new CustomException("修改轮播图失败!");
            }
        });
        return Boolean.TRUE;
    }
}
