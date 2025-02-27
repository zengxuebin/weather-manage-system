package com.weather.common.constant;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里短信常量
 * @author linkaixuan
 * @since 2024/5/20 00:09
 */
@Data
@Component
@ConfigurationProperties("aliyun.sms")
public class AliSmsConstants implements InitializingBean {

    private String accessKey;
    private String accessSecret;

    private AliSmsConstants() {
    }

    /**
     * 短信API产品域名
     */
    public static final String DO_MAIN = "dysmsapi.aliyuncs.com";
    /**
     * 短信模板
     */
    public static final String TEMPLATE_CODE = "SMS_460816151";
    /**
     * 签名
     */
    public static final String SIGN_NAME = "气象信息智慧监测平台";
    /**
     * AccessKey ID
     */
    public static String ACCESS_KEY;
    /**
     * AccessKey Secret
     */
    public static String ACCESS_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY = accessKey;
        ACCESS_SECRET = accessSecret;
    }
}
