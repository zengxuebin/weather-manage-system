package com.weather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weather.domain.entity.WeatherExport;
import com.weather.mapper.WeatherExportMapper;
import com.weather.service.WeatherExportService;
import org.springframework.stereotype.Service;

/**
 * 气象数据导出 业务实现层
 * @author linkaixuan
 * @since 2024/5/4 04:13
 */
@Service
public class WeatherExportServiceImpl extends ServiceImpl<WeatherExportMapper, WeatherExport> implements WeatherExportService {
}
