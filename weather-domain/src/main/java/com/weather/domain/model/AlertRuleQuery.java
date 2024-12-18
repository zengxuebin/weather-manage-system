package com.weather.domain.model;

import lombok.Data;

/**
 * @Description: 预警规则查询条件
 * @Author: linkaixuan
 * @Date: 2024/5/17 01:00
 */
@Data
public class AlertRuleQuery {

    private String ruleName;
    private String alertLevel;
    private String metric;
}
