package com.lin.item.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: L
 * @Date: 2023/3/7 21:50
 * @Desc: 列表查询结果
 */
@Data
public class IPage<T> {

    private Long total;

    private Long pageNum;

    private List<T> list;

    public IPage(Long total, Long current, List<T> result) {
        this.total = total;
        this.pageNum = current;
        this.list = result;
    }
}
