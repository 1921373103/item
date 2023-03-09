package com.lin.item.vo;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lin.item.common.entity.BaseEntity;
import com.lin.item.common.enums.DataEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: L
 * @Date: 2023/3/9 22:06
 * @Desc: 系统用户 - VO
 */
@Data
@TableName("sys_user")
public class SysUserVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer sysUserId;

    /** 人员登录名 */
    private String sysUserLogin;

    /** 人员密码 */
    private String sysUserPwd;

    /** 人员姓名 */
    private String sysUserName;

    /** 联系电话 */
    private String sysUserContact;

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
