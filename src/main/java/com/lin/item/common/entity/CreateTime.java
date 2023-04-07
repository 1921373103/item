package com.lin.item.common.entity;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: L
 * @Date: 2023/3/14 17:12
 * @Desc:
 */
@Data
public class CreateTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeS;

    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeE;
}
