package com.weather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weather.domain.entity.AlertLog;
import com.weather.mapper.AlertLogMapper;
import com.weather.service.AlertLogService;
import org.springframework.stereotype.Service;

/**
 * 预警记录 业务实现层
 * @author linkaixuan
 * @since 2024/5/4 04:07
 */
@Service
public class AlertLogServiceImpl extends ServiceImpl<AlertLogMapper, AlertLog> implements AlertLogService {
}
