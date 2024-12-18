package com.weather.domain.model;

import lombok.Data;

/**
 * 预警规则查询条件
 * @author linkaixuan
 * @since 2024/5/17 01:00
 */
@Data
public class AlertRuleQuery {

    private String ruleName;
    private String alertLevel;
    private String metric;
}
