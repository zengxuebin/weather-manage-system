package com.weather.domain.model;

import lombok.Data;

/**
 * @Description: 气象数据条件
 * @Author: linkaixuan
 * @Date: 2023/5/16 14:32
 */
@Data
public class WeatherDataQuery {

    private Long stationNo;
    private Integer windDirection;

}
