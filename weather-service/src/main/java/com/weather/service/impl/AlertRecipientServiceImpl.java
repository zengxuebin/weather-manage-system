package com.weather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weather.domain.entity.AlertRecipient;
import com.weather.mapper.AlertRecipientMapper;
import com.weather.service.AlertRecipientService;
import org.springframework.stereotype.Service;

/**
 * 预警接收 业务实现层
 * @author linkaixuan
 * @since 2024/5/4 04:09
 */
@Service
public class AlertRecipientServiceImpl extends ServiceImpl<AlertRecipientMapper, AlertRecipient> implements AlertRecipientService {
}
