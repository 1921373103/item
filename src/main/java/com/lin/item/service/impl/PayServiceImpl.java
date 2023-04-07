package com.lin.item.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lin.item.common.config.WechatPayConfig;
import com.lin.item.common.exception.CustomException;
import com.lin.item.common.util.WechatPayUtil;
import com.lin.item.dao.PhoneCardDao;
import com.lin.item.entity.PhoneCard;
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
     * 	"sp_mchid": "1900007XXX",
     * 	"sub_mchid": "1900008XXX",
     * 	"out_trade_no": "1217752501201407033233368318",
     * 	"sp_appid": "wxdace645e0bc2cXXX",
     * 	"sub_appid": "wxdace645e0bc2cXXX",
     * 	"description": "Image形象店-深圳腾大-QQ公仔",
     * 	"notify_url": "https://weixin.qq.com/",
     * 	"amount": {
     * 		"total": 1,
     * 		"currency": "CNY"
     *        },
     * 	"payer": {
     * 		"sp_openid": "o4GgauInH_RCEdvrrNGrntXDuXXX"
     *    }
     * }
     */

    @Override
    @Transactional(rollbackFor = CustomException.class)
    public Map<String, String> orderPay(JSONObject jsonObject, String campusTelephoneCard) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 15);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        jsonObject.put("out_trade_no", createOutTradeNO());
        jsonObject.put("time_expire", sdf.format(calendar.getTime()));

        //发起请求
        JSONObject res = wechatPayUtil.sendPost(WechatPayConfig.WX_ORDER_URL, jsonObject);

        // 修改状态
        PhoneCard phoneCard = new PhoneCard();
        phoneCard.setCardStatus(1);
        phoneCard.setCampusTelephoneCard(campusTelephoneCard);
        phoneCardDao.updateByCampusTelephoneCard(phoneCard);
        if ((res == null || StringUtils.isEmpty(res.getString("prepay_id")))) {
            //@TODO 支付发起失败可以将订单数据回滚
            throw new CustomException("支付发起失败");
        }

        StringBuilder sb = new StringBuilder();

        Map<String, String> result = new HashMap<>();
        //小程序appid
        result.put("appId", WechatPayConfig.APP_ID);
        sb.append(result.get("appId")).append("\n");
        //时间戳
        result.put("timeStamp", (System.currentTimeMillis() / 1000) + "");
        sb.append(result.get("timeStamp")).append("\n");
        //32位随机字符串
        result.put("nonceStr", RandomStringUtils.randomAlphanumeric(32));
        sb.append(result.get("nonceStr")).append("\n");
        //预支付id 格式为 prepay_id=xxx
        result.put("package", "prepay_id=" + res.getString("prepay_id"));
        sb.append(result.get("package")).append("\n");
        //签名
        result.put("paySign", wechatPayUtil.signRSA(sb.toString()));
        //加密方式 固定RSA
        result.put("signType", "RSA");
        //商户订单号 此参数不是小程序拉起支付所需的参数 因此不参与签名
        result.put("out_trade_no", res.getString("out_trade_no"));
        return result;
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
     * 创建订单
     *
     * @return
     */
    public String createOutTradeNO() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    }


}
