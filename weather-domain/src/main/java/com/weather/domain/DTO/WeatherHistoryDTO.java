package com.weather.domain.DTO;

import lombok.Data;

/**
 * 历史天气统计
 * @author linkaixuan
 * @since 2024/5/15 09:36
 */
@Data
public class WeatherHistoryDTO {

    private Float maxTemp;
    private Float minTemp;
    private Float sumPrecipitation;
    private Float maxPrecipitation;
    private Float maxVisibility;
    private Float maxWindSpeed;

}
