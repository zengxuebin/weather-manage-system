package com.weather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weather.domain.entity.AlertSubscription;
import com.weather.mapper.AlertSubscriptionMapper;
import com.weather.service.AlertSubscriptionService;
import org.springframework.stereotype.Service;

/**
 * @Description: 预警订阅 业务实现层
 * @Author: linkaixuan
 * @Date: 2024/5/4 04:11
 */
@Service
public class AlertSubscriptionServiceImpl extends ServiceImpl<AlertSubscriptionMapper, AlertSubscription> implements AlertSubscriptionService {
}
