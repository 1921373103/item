package com.lin.item.entity;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lin.item.common.enums.DataEnum;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: L
 * @Date: 2023/3/9 22:52
 * @Desc: 校园电话卡
 */
@Data
@TableName("phone_card")
public class PhoneCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 校园电话卡ID */
    @TableId(type = IdType.AUTO)
    private Integer cardId;

    /** 真实姓名 */
    private String realName;

    /** 身份证号码 */
    private String idNumber;

    /** 联系电话 */
    private String contactNumber;

    /** 楼栋 */
    private String building;

    /** 寝室号 */
    private String bedroomNumber;

    /** 校园电话卡 */
    private String campusTelephoneCard;

    /** 售后服务 */
    private String afterCare;

    /** 备注 */
    private String remark;

    /** 卡状态 (未交钱、交钱已激活、交钱未激活) */
    private Integer cardStatus;

    /** 商户订单号 */
    private String outTradeNo;

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
