package com.weather.domain.DTO;

import lombok.Data;

/**
 * @Description: 监测站个数
 * @Author: linkaixuan
 * @Date: 2024/5/15 13:21
 */
@Data
public class StationCountDTO {

    private String stationCity;
    private Integer count;
}
