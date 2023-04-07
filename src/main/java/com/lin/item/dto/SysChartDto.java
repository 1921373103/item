package com.lin.item.dto;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lin.item.common.enums.DataEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: L
 * @Date: 2023/3/9 20:32
 * @Desc: 轮播图
 */
@Data
@TableName("sys_chart")
public class SysChartDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 轮播图主键 */
    @TableId(type = IdType.AUTO)
    private Integer chartId;

    /** 图片ID */
    private Integer fileId;

    /** 图片地址 */
    private String fileUrl;

    /** 0：在用，1：停用 */
    private Integer status = DataEnum.USING.getCode();

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 修改人 */
    private String updateBy;

    /** 修改时间 */
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 0：在用，1：停用 */
    @TableLogic
    private Integer isDel = DataEnum.USING.getCode();
}
