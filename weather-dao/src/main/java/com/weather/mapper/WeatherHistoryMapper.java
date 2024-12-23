package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.DTO.TempRangeDTO;
import com.weather.domain.DTO.WeatherHistoryDTO;
import com.weather.domain.entity.WeatherHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 历史天气 mapper层
 * @author linkaixuan
 * @since 2024/5/15 09:24
 */
@Mapper
public interface WeatherHistoryMapper extends BaseMapper<WeatherHistory> {

    /**
     * 返回统计结果集
     * @param nowCity 当前城市
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 结果集
     */
    WeatherHistoryDTO statisticsWeatherHistory(String nowCity, Date startDate, Date endDate);

    /**
     * 返回温差结果集
     * @param nowCity 当前城市
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 结果集
     */
    List<Map<String, TempRangeDTO>> selectTemperatureRange(String nowCity, Date startDate, Date endDate);
}
