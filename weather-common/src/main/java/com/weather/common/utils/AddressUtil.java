package com.weather.common.utils;

import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


/**
 * 获取地址类
 * @author linkaixuan
 * @since 2024/3/24 11:10
 */
public class AddressUtil {

    private AddressUtil() {
    }

    /**
     * ip地址查询
     */
    public static final String IP_URL = "https://whois.pconline.com.cn/ipJson.jsp";

    /**
     * 获取ip地址信息
     */
    public static String getIpInfo(String key) {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        try {
            result = restTemplate.getForObject(IP_URL, String.class);
        } catch (Exception e) {
            result = "{\"ip\":\"39.144.168.43\",\"pro\":\"江西省\",\"proCode\":\"360000\",\"city\":\"南昌市\",\"cityCode\":\"360100\",\"region\":\"\",\"regionCode\":\"0\",\"addr\":\"江西省南昌市 \",\"regionNames\":\"\",\"err\":\"\"}";
        }
        assert result != null;
        int prefixIndex = result.indexOf("{", result.indexOf("{") + 1);
        int suffixIndex = result.lastIndexOf("}", result.lastIndexOf("}") - 1);
        String ipInfo = result.substring(prefixIndex, suffixIndex + 1);
        HashMap<?, ?> map = new HashMap<>();
        Gson gson = new Gson();
        map = gson.fromJson(ipInfo, map.getClass());
        return map.get(key).toString();
    }
}
