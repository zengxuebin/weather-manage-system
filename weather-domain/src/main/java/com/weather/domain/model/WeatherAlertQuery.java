package com.weather.domain.model;

import lombok.Data;

/**
 * 预警信息查询条件
 * @author linkaixuan
 * @since 2024/5/17 07:46
 */
@Data
public class WeatherAlertQuery {

    private String alertTitle;
    private String alertType;
    private String alertStatus;
    private String alertLevel;
    private String alertRuleId;
    private String alertSource;
    private String alertAreaId;
}
