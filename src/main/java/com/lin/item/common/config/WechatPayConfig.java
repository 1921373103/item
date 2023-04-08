package com.lin.item.common.config;

import org.springframework.context.annotation.Configuration;

/**
 * @Author: L
 * @Date: 2023/4/7 18:19
 * @Desc:
 */
@Configuration
public class WechatPayConfig {

    /**
     * 应用编号
     **/
    public static final String APP_ID = "wxa08311d097dc10c3";

    /**
     * 微信支付商户号
     **/
    public static final String MCH_ID = "1641478481";

    /**
     * 回调地址
     **/
    public static final String NOTIFY_URL = null;

    /**
     * V3 密钥
     **/
    public static final String KEY = "FD30811350D3DB7E36AA3942E9D7E250";

    /**
     * 私钥
     **/
    public static final String PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDIosnbBS2QfWjU\n" +
            "Eh4pNUD31DuSZcIze0MiEKcfysPp1WMhUZFVqIPAYLbOeaoZeqzanv6bSPODxGuf\n" +
            "PqCjEo8X9VamtahVKsKQD0ZYXuJeogRn++oP8TcdkGHJO00+wqoVIZSKz91XZLnt\n" +
            "DgR5Tcr6I1jHSG8eAJLybo8LHTeeZFSwCSEfgpN1Up9rlBxjGb34/tPpTH5ROp8U\n" +
            "Qi0vm/XF/4/r5a+CujvBLAgtOBVWhKlE7J2NqsgWtUUZYBeIaSkkv3rxZZL1Jbeh\n" +
            "RsRNoun+K6G+o8PbGPrtxISa51ahGMFsVCbdkzi4sRUTwhM+juCZz1nnA0+bexA5\n" +
            "MJaUPUo1AgMBAAECggEAbtBiluKsQOLTVFZRbEcqg/cnk/s6IL8S3CzAcbHHNU6T\n" +
            "euKXV+1EH6wLK81M4te7wuAQFepb/UouevzXMlpcyEnMOzWYLLn6TiDxpHt7ofLw\n" +
            "ZhJgO7/pxh1ptQBY8LIJyLQMXALk6JEMk96rH+iUlEWTkjXykWqVvurLfML9ZjBf\n" +
            "bgR1CiKqq+s5/9mpyJpivJKyvARiFDps4mhKbY1AqfPuujht1wrwnxrMZ5j6kzff\n" +
            "GotTYsBb9iKwX+LNhS4p1l3TLaTVfG/sQC70fQQ65hxO0/q9Gnn6OfwbTSlN1zW8\n" +
            "NB5avAIj19HJPHwc5EnuMaX68IiPveBZkGKutCYUgQKBgQDtMZQqV/h+oGCCjDyR\n" +
            "a+iYjaJezJ18JtzfzerCeO9zHHCNirwgeZxKsjlpud+gnVcHD1rMlzZ0aV75Y3j1\n" +
            "Bl0U8CtiNgeQsGefyWHXhtnj1lHx5M3QejTm5d/Y32JusHftJ86LR3IQ/3kg9Krs\n" +
            "RJ1D6lKyBXFBUHc3JD0ibGj1lQKBgQDYiy5TCOs3F++zoJrhv86GrfAQRMFMCiBo\n" +
            "SQq5jQM3c/BAB1Wl0ABx6tjg/CiYgiM6U6cP8SUTUl3+di3gknF51tfaf5rzbb7Y\n" +
            "U0Vd2bLowqSH97LSSs9vnJo5gQ3pp6h6hRMtXhFB5+Yq+R+PBdVyQQdgfuSKFduw\n" +
            "DoGbyJKaIQKBgQCfiSlPEbxh7xz6sbhLV3kqopkDijyitMD7B1VPWGg11HsfuKCw\n" +
            "dxtYYmebZRm0vh868GjyEwLdacInm3BP3efdnWyQsiaEeyI3pwvYGyuSwyR6vppw\n" +
            "DsJmS1c0V4utR6RiLtQNehCOvkaKlAYixEPC2miktGRoueZ1gDpeXOx7wQKBgCSm\n" +
            "Sczb8ZN9haBNSmly4S2COuNF/8Ey5ZFA64PEDgtQoBT2KNTLg4fES7iodOZ7ACNb\n" +
            "DSdxlOjUQ/xRXCpqHrKUXiwo9LzGkkpirZbYxaMHbnlkvutApiMpyiB5azVknus9\n" +
            "oqVHCnyxFw+m4mR+hEW2I919HIkScgA2NFF9fPKBAoGBAOWxUTNjEQqWTbmNBP1L\n" +
            "cfsD5vMMdC378B1klQZDRJ6aRCMejTZ1dH+UldqT4kxHwb1uEhE+aKuvdT2YEZpS\n" +
            "O7ARjdeS0/P7JMPPsotGEMWGCiWOM0wAcc+w6S7NZwOh/7HYghrvfEtRsOq6QyOe\n" +
            "KNozt8k9EHC4suXut+2+6iYL"
            + "-----END PRIVATE KEY-----";

    /**
     * 证书序列号
     **/
    public static final String MCH_SERIAL_NO = "42236C4F027E8287310783203001940A911E4183";

    /**
     * 签名固定字符串
     **/
    public static final String WX_SIGN = "SIGN=WXPay";

    /**
     * 下单地址
     **/
    public static final String WX_ORDER_URL = "https://api.mch.weixin.qq.com/v3/pay/partner/transactions/h5";

    /**
     * 获取证书
     */
    public static final String CERTIFICATES_URL = "https://api.mch.weixin.qq.com/v3/certificates";

}
