package com.weather.common.factory;

import com.weather.common.strategy.*;
import com.weather.domain.entity.WeatherData;

import java.time.LocalDate;
import java.time.Month;

/**
 * 数据工厂 根据当前季节选择合适的生成策略
 *
 * @author zengxuebin
 * @since 2024/12/24 21:16
 */
public class WeatherDataFactory {

    public static WeatherGenerationStrategy getGenerationStrategy(LocalDate date) {
        Month month = date.getMonth();
        if (month == Month.MARCH || month == Month.APRIL || month == Month.MAY) {
            return new SpringGenerationStrategy();
        } else if (month == Month.JUNE || month == Month.JULY || month == Month.AUGUST) {
            return new SummerGenerationStrategy();
        } else if (month == Month.SEPTEMBER || month == Month.OCTOBER || month == Month.NOVEMBER) {
            return new AutumnGenerationStrategy();
        } else {
            return new WinterGenerationStrategy();
        }
    }

    public static WeatherData createWeatherData(Long stationNo, LocalDate date) {
        WeatherData weatherData = new WeatherData();
        weatherData.setStationNo(stationNo);
        weatherData.setDataCollectTime(date.atStartOfDay());
        WeatherGenerationStrategy strategy = getGenerationStrategy(date);
        strategy.generate(weatherData);
        return weatherData;
    }
}
