package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.WeatherData;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * 气象数据 mapper层
 * @author linkaixuan
 * @since 2024/5/4 03:58
 */
@Mapper
public interface WeatherDataMapper extends BaseMapper<WeatherData> {
    List<WeatherData> getLatestDataForEachStation(LocalDate today);
}
