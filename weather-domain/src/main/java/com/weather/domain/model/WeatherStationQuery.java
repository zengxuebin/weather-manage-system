package com.weather.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 监测站分页查询条件
 * @author linkaixuan
 * @since 2024/5/14 14:13
 */
@Setter
@Getter
public class WeatherStationQuery {

    private String stationProvince;
    private String stationCity;
    private String stationType;

}
