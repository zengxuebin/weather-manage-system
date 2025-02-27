package com.weather.common.utils;

import java.util.Map;

/**
 * 预警信息模板
 * @author linkaixuan
 * @since 2024/5/17 19:32
 */
public class AlertTemplateUtil {

    private AlertTemplateUtil() {
    }

    /**
     * 生成预警详情模板
     *
     * @param template 预警详情模板
     * @param dataMap  数据映射
     * @return 生成预警详情文本
     */
    public static String generateAlertDetails(String template, Map<String, String> dataMap) {
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            String placeholder = "{" + entry.getKey() + "}";
            String value = entry.getValue();
            template = template.replace(placeholder, value);
        }
        return template;
    }
}
