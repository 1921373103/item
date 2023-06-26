package com.lin.item.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lin.item.common.config.RedisConfig;
import com.lin.item.common.config.WechatPayConfig;
import com.lin.item.common.exception.CustomException;
import com.lin.item.common.util.WechatPayUtil;
import com.lin.item.dao.PhoneCardDao;
import com.lin.item.dao.SysUserDao;
import com.lin.item.entity.PhoneCard;
import com.lin.item.entity.SysUser;
import com.lin.item.service.IPayService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: L
 * @Date: 2023/4/7 17:25
 * @Desc: 微信支付Serivce
 */
@Service("payServiceImpl")
public class PayServiceImpl implements IPayService {

    @Autowired
    public WechatPayUtil wechatPayUtil;

    @Autowired
    private PhoneCardDao phoneCardDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private RedisConfig redisConfig;

    /**
     *         JSONObject params = new JSONObject();
     *         params.put("appid", WechatConstants.WECHAT_MP_APPID); //小程序appid
     *         params.put("mchid", WechatConstants.WECHAT_MCH_ID); //商户号
     *         params.put("description", content); //商品描述
     *         params.put("out_trade_no", out_trade_no); //商户订单号
     *         params.put("time_expire", sdf.format(calendar.getTime())); //交易结束时间 选填 时间到了之后将不能再支付 遵循rfc3339标准格式
     *         params.put("attach", attach); //附加数据 选填 在查询API和支付通知中原样返回 可作为自定义参数使用
     *         params.put("notify_url", WechatUrlConstants.PAY_V3_NOTIFY); //支付结果异步通知接口
     *         JSONObject amount_json = new JSONObject();
     *         amount_json.put("total", Integer.parseInt(amount_fee(amount))); //支付金额 单位：分
     *         params.put("amount", amount_json); //订单金额信息
     *         JSONObject payer = new JSONObject();
     *         payer.put("openid", openid); //用户在小程序侧的openid
     *         params.put("payer", payer); //支付者信息
     *         {
     * 	"sp_mchid": "1900006XXX",
     * 	"out_trade_no": "H51217752501201407033233368018",
     * 	"sp_appid": "wxdace645e0bc2cXXX",
     * 	"sub_mchid": "1900006XXX",
     * 	"description": "Image形象店-深圳腾大-QQ公仔",
     * 	"notify_url": "https://weixin.qq.com/",
     * 	"amount": {
     * 		"total": 1,
     * 		"currency": "CNY"
     *        },
     * 	"scene_info": {
     * 		"payer_client_ip": "127.0.0.1",
     * 		"h5_info": {
     * 			"type": "Wap"
     *        }
     *    }
     * }
     */

    @Override
    @Transactional(rollbackFor = CustomException.class)
    public String orderPay(JSONObject jsonObject) {

        JSONObject wxPay = jsonObject.getJSONObject("wxPay");

        String outTradeNO = createOutTradeNO();

        wxPay.put("out_trade_no", outTradeNO);

        //发起请求
        JSONObject res = wechatPayUtil.sendPost(WechatPayConfig.WX_ORDER_URL, wxPay);

        // 设置实体
        PhoneCard phoneCard = setPhoneCard(jsonObject.getJSONObject("subForm"), outTradeNO);
        phoneCardDao.insert(phoneCard);
        if ((res == null || StringUtils.isEmpty(res.getString("h5_url")))) {
            //@TODO 支付发起失败可以将订单数据回滚
            throw new CustomException("支付发起失败");
        }
        return res.getString("h5_url");
    }

    /**
     * 设置实体
      */
    @Transactional(rollbackFor = CustomException.class)
    public PhoneCard setPhoneCard(JSONObject subForm, String outTradeNO) {
        PhoneCard phoneCard = new PhoneCard();
        phoneCard.setRealName(subForm.getString("realName"));
        phoneCard.setIdNumber(subForm.getString("idNumber"));
        phoneCard.setContactNumber(subForm.getString("contactNumber"));
        phoneCard.setBuilding(subForm.getString("building"));
        phoneCard.setBedroomNumber(subForm.getString("bedroomNumber"));
        if (null == phoneCardDao.selectOne(new QueryWrapper<PhoneCard>().lambda().eq(PhoneCard::getCampusTelephoneCard, subForm.getString("campusTelephoneCard")))) {
            throw new CustomException("校园电话卡已存在!");
        }
        phoneCard.setCampusTelephoneCard(subForm.getString("campusTelephoneCard"));
        SysUser sysUser = sysUserDao.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getSysUserName, subForm.getString("afterCare")));
        if (null == sysUser) {
            throw new CustomException("售后人员不存在!");
        }
        phoneCard.setAfterCare(subForm.getString("afterCare"));
        phoneCard.setRemark(subForm.getString("remark"));
        phoneCard.setOutTradeNo(outTradeNO);
        phoneCard.setCardStatus(0);
        phoneCard.setCreateTime(DateUtil.date());
        return phoneCard;
    }

    @Override
    public Map<String, String> wechatPayNotify(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>(2);
        JSONObject res = wechatPayUtil.getCallbackData(request);
        if (res == null) {
            result.put("code", "FAIL");
            result.put("message", "失败");
            return result;
        }
        //@TODO 处理支付成功后的业务 例如 将订单状态修改为已支付 具体参数键值可参考文档 注意！！！ 微信可能会多次发送重复的通知 因此要判断业务是否已经处理过了 避免重复处理


        result.put("code", "SUCCESS");
        result.put("message", "OK");
        return result;
    }

    /**
     * 查询订单接口
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public Map<String, String> getOrderByoutTradeNo(String outTradeNo) {
        Map<String, String> result = new HashMap<>(2);
        String mchid = "1641478481";
        String Url = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/" + outTradeNo + "?mchid=" + mchid;
        JSONObject s = wechatPayUtil.sendGet(Url);
        if (Objects.equals(s.getString("trade_state"), "SUCCESS")) {
            result.put("code", "SUCCESS");
            result.put("message", "支付成功");
            PhoneCard phoneCard = new PhoneCard();
            phoneCard.setOutTradeNo(outTradeNo);
            phoneCard.setCardStatus(1);
            phoneCardDao.update(phoneCard, new UpdateWrapper<PhoneCard>().lambda().eq(PhoneCard::getOutTradeNo, outTradeNo));
            return result;
        }
        result.put("code", "FAIL");
        result.put("message", "支付失败");
        return result;
    }

    /**
     * 创建订单
     *
     * @return
     */
    public String createOutTradeNO() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    }


}
