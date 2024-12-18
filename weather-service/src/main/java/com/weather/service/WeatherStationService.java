package com.weather.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weather.domain.DTO.StationCountDTO;
import com.weather.domain.DTO.StationTypeCountDTO;
import com.weather.domain.entity.WeatherStation;

import java.util.List;
import java.util.Map;

/**
 * @Description: 气象站台 业务层
 * @Author: linkaixuan
 * @Date: 2023/5/4 04:06
 */
public interface WeatherStationService extends IService<WeatherStation> {

    /**
     * 获取所有城市列表
     * @return 城市列表
     */
    List<Map<String, Object>> getAllCity();

    /**
     * 获取监测站个数
     * @return 监测站个数
     */
    List<StationCountDTO> countStation();

    /**
     * 统计监测站类型个数
     * @return 类型个数
     */
    List<StationTypeCountDTO> countStationType();
}
