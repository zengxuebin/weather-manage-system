package com.weather.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weather.domain.DTO.TempRangeDTO;
import com.weather.domain.DTO.WeatherHistoryDTO;
import com.weather.domain.entity.WeatherHistory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 历史天气 业务层
 * @author linkaixuan
 * @since 2024/5/15 09:25
 */
public interface WeatherHistoryService extends IService<WeatherHistory> {

    /**
     * 根据条件统计历史天气
     * @param nowCity 当前城市
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 历史天气
     */
    WeatherHistoryDTO statisticsWeatherHistory(String nowCity, Date startDate, Date endDate);

    /**
     * 根据条件统计温差
     * @param nowCity 当前城市
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 温差
     */
    List<Map<String, TempRangeDTO>> getTemperatureRange(String nowCity, Date startDate, Date endDate);
}
