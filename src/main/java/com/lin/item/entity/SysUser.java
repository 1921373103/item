package com.lin.item.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lin.item.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: L
 * @Date: 2023/3/8 11:13
 * @Desc: 系统用户
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {

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
    private Date createTime;

    /** 修改人 */
    private String updateBy;

    /** 修改时间 */
    private Date updateTime;
}
