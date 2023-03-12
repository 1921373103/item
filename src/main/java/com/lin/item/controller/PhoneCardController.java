package com.lin.item.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.item.common.entity.Result;
import com.lin.item.common.interfaces.SelectGroup;
import com.lin.item.entity.PhoneCard;
import com.lin.item.service.IPhoneCardService;
import com.lin.item.vo.PhoneCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;

/**
 * @Author: L
 * @Date: 2023/3/9 23:02
 * @Desc: 校园电话卡 - Controller
 */
@RestController
@RequestMapping("/phoneCard")
public class PhoneCardController {

    @Autowired
    private IPhoneCardService iPhoneCardService;

    /**
     * 查询校园卡(管理员查所有) - 分页
     */
    @SaCheckLogin
    @GetMapping("/list")
    public Result list(@Validated(SelectGroup.class) PhoneCardVo phoneCardVo) {
        return Result.success(iPhoneCardService.queryPage(phoneCardVo));
    }

    /**
     * 根据校园电话卡查询信息
     */
    @GetMapping("/{campusTelephoneCard}")
    public Result getInfoByCampusTelephoneCard(@PathVariable String campusTelephoneCard) {
        return Result.success(iPhoneCardService.getOne(new QueryWrapper<PhoneCard>().lambda().eq(PhoneCard::getCampusTelephoneCard, campusTelephoneCard)));
    }

    /**
     * 新增校园电话卡登记
     */
    @PostMapping
    public Result add(@RequestBody PhoneCard phoneCard) {
        return Result.toAjax(iPhoneCardService.add(phoneCard));
    }

    /**
     * 修改校园电话卡信息
     */
    @SaCheckLogin
    @PutMapping
    public Result edit(@RequestBody PhoneCard phoneCard) {
        return Result.toAjax(iPhoneCardService.edit(phoneCard));
    }

    /**
     * 删除校园电话卡登记信息
     */
    @SaCheckLogin
    @DeleteMapping("/{cardId}")
    public Result remove(@PathVariable Integer[] cardId){
        return Result.toAjax(iPhoneCardService.removeByIds(Arrays.asList(cardId)));
    }

}
