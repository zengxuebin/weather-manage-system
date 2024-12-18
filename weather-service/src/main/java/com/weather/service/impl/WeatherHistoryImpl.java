package com.weather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weather.domain.DTO.TempRangeDTO;
import com.weather.domain.DTO.WeatherHistoryDTO;
import com.weather.domain.entity.WeatherHistory;
import com.weather.mapper.WeatherHistoryMapper;
import com.weather.service.WeatherHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 历史天气 业务实现层
 * @Author: linkaixuan
 * @Date: 2024/5/15 09:25
 */
@Service
public class WeatherHistoryImpl extends ServiceImpl<WeatherHistoryMapper, WeatherHistory> implements WeatherHistoryService {

    @Autowired
    private WeatherHistoryMapper historyMapper;

    /**
     * 根据条件统计历史天气
     *
     * @param nowCity   当前城市
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 历史天气
     */
    @Override
    public WeatherHistoryDTO statisticsWeatherHistory(String nowCity, Date startDate, Date endDate) {
        return historyMapper.statisticsWeatherHistory(nowCity, startDate, endDate);
    }

    /**
     * 根据条件统计温差
     *
     * @param nowCity   当前城市
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 温差
     */
    @Override
    public List<Map<String, TempRangeDTO>> getTemperatureRange(String nowCity, Date startDate, Date endDate) {
        return historyMapper.selectTemperatureRange(nowCity, startDate, endDate);
    }
}
