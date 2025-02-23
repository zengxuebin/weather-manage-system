package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.DTO.StationCountDTO;
import com.weather.domain.DTO.StationTypeCountDTO;
import com.weather.domain.entity.WeatherStation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 气象站 mapper层
 * @author linkaixuan
 * @since 2024/5/4 04:00
 */
@Mapper
public interface WeatherStationMapper extends BaseMapper<WeatherStation> {
    /**
     * 获取监测站个数
     * @return 监测站个数
     */
    List<StationCountDTO> getCountStation();

    /**
     * 统计监测站类型
     * @return 类型个数
     */
    List<StationTypeCountDTO> getCountStationType();
}
