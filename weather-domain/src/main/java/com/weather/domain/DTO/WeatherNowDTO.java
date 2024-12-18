package com.weather.domain.DTO;

import lombok.Data;

/**
 * 实况天气
 * @author linkaixuan
 * @since 2024/5/16 10:35
 */
@Data
public class WeatherNowDTO {

    private Float temperature;
    private AirQualityDTO airQuality;
}
