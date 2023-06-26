package com.lin.item;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.item.common.util.WechatPayUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@SpringBootTest
class ItemApplicationTests {

    @Autowired
    public WechatPayUtil wechatPayUtil;

    @Test
    void contextLoads() {
    }

    private void Order() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("mchid", "1641478481");
        String outTradeNo = "123456789";
        String mchid = "1641478481";
        String Url = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/" + outTradeNo + "?mchid=" + mchid;
        System.out.println("Url = " + Url);
        JSONObject s = wechatPayUtil.sendGet(Url);
//        String s = HttpUtil.get("https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/" + outTradeNo, paramMap);
        System.out.println("s = " + s);
    }

    public static void main(String[] args) {
        JSONObject jsonObject = JSON.parseObject("{\n" +
                "\t\"sp_mchid\": \"1900007XXX\",\n" +
                "\t\"sub_mchid\": \"1900008XXX\",\n" +
                "\t\"out_trade_no\": \"1217752501201407033233368318\",\n" +
                "\t\"sp_appid\": \"wxdace645e0bc2cXXX\",\n" +
                "\t\"sub_appid\": \"wxdace645e0bc2cXXX\",\n" +
                "\t\"description\": \"Image形象店-深圳腾大-QQ公仔\",\n" +
                "\t\"notify_url\": \"https://weixin.qq.com/\",\n" +
                "\t\"amount\": {\n" +
                "\t\t\"total\": 1,\n" +
                "\t\t\"currency\": \"CNY\"\n" +
                "\t},\n" +
                "\t\"payer\": {\n" +
                "\t\t\"sp_openid\": \"o4GgauInH_RCEdvrrNGrntXDuXXX\"\n" +
                "\t}\n" +
                "}");
        jsonObject.put("notify_url", "123231213");
//        System.out.println("jsonObject = " + jsonObject);
        System.out.println("(System.currentTimeMillis() / 1000) + \"\" = " + (System.currentTimeMillis() / 1000) + "");
    }
}
