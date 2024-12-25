package com.weather.common.strategy;

import com.weather.domain.entity.WeatherData;

/**
 * 气象数据生成接口
 *
 * @author zengxuebin
 * @since 2024/12/24 21:03
 */
public interface WeatherGenerationStrategy {

    void generate(WeatherData weatherData);

}
