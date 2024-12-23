package com.weather.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 预测天气
 * @author linkaixuan
 * @since 2024/5/15 21:27
 */
@Data
@AllArgsConstructor
public class ForecastWeatherDTO {

    private Date datetime;
    private Float precipitation;
    private Float temperature;
    private Float pressure;
    private Float visibility;
    private Float cloudRate;
    private Float humidity;

}
