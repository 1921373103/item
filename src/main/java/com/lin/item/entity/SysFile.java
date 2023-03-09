package com.lin.item.entity;

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
 * @Date: 2023/3/9 18:21
 * @Desc: 图片附件
 */
@Data
@TableName("sys_file")
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 参数主键 */
    @TableId(type = IdType.AUTO)
    private Integer fileId;

    /** 文件名称 */
    private String fileName;

    /** 文件组 */
    private String fileGroup;

    /** 文件访问地址 */
    private String fileUrl;

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
