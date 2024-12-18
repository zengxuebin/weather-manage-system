package com.weather.domain.model;

import lombok.Data;

/**
 * 气象数据条件
 * @author linkaixuan
 * @since 2024/5/16 14:32
 */
@Data
public class WeatherDataQuery {

    private Long stationNo;
    private Integer windDirection;

}
