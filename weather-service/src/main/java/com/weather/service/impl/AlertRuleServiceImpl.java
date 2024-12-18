package com.weather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weather.domain.entity.AlertRule;
import com.weather.mapper.AlertRuleMapper;
import com.weather.service.AlertRuleService;
import org.springframework.stereotype.Service;

/**
 * @Description: 预警规则 业务实现层
 * @Author: linkaixuan
 * @Date: 2024/5/4 04:10
 */
@Service
public class AlertRuleServiceImpl extends ServiceImpl<AlertRuleMapper, AlertRule> implements AlertRuleService {
}
