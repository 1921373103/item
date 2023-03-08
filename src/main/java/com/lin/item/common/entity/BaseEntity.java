package com.lin.item.common.entity;

import com.lin.item.common.interfaces.SelectGroup;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: L
 * @Date: 2023/3/7 21:47
 * @Desc: 基础实体类
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 分页起始页 */
    @NotNull(message = "分页参数不能为空！", groups = {SelectGroup.class})
    private Integer pageNum;

    /** 分页数 */
    @NotNull(message = "分页参数不能为空！", groups = {SelectGroup.class})
    private Integer pageSize;

    /** 排序名称 */
    private String srotName;

    /** 排序方式 */
    private String srotOrder;

    /** 创建时间区间查询 */
    private String[] createTimeBetween;

    /** AOP等数据 */
    private Map<String, Object> params;

}
