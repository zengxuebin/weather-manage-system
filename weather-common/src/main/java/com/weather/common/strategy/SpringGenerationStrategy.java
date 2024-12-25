package com.weather.common.strategy;

import com.weather.domain.entity.WeatherData;

import java.util.Random;

/**
 * 春天气象数据生成策略
 *
 * @author zengxuebin
 * @since 2024/12/24 21:05
 */
public class SpringGenerationStrategy implements WeatherGenerationStrategy {

    private static final Random random = new Random();

    @Override
    public void generate(WeatherData weatherData) {
        weatherData.setWeather("晴朗");
        weatherData.setTemperature(random.nextFloat() * (30 - 10) + 10); // 10°C to 30°C
        weatherData.setHumidity(random.nextFloat() * (80 - 40) + 40); // 40% to 80%
        weatherData.setPressure(random.nextFloat() * (1020 - 980) + 980); // 980 hPa to 1020 hPa
        weatherData.setWindSpeed(random.nextFloat() * (10 - 2) + 2); // 2 m/s to 10 m/s
        weatherData.setWindDirection(random.nextInt(360)); // 0° to 360°
        weatherData.setPrecipitation(random.nextFloat() * (50) + 0); // 0 mm to 50 mm
        weatherData.setClouds(random.nextFloat() * (70 - 30) + 30); // 30% to 70%
        weatherData.setVisibility(random.nextInt(10000 - 5000) + 5000); // 5000m to 10000m
        weatherData.setAirQualityDesc("良好");
        weatherData.setAqi(random.nextInt(100 - 50) + 50); // 50 to 100
        weatherData.setPm25(random.nextInt(50 - 20) + 20); // 20 to 50
        weatherData.setPm10(random.nextInt(70 - 40) + 40); // 40 to 70
        weatherData.setNo2(random.nextInt(40 - 20) + 20); // 20 to 40
        weatherData.setSo2(random.nextInt(30 - 10) + 10); // 10 to 30
        weatherData.setO3(random.nextInt(100 - 50) + 50); // 50 to 100
        weatherData.setCo(random.nextFloat() + 0.5f); // 0.5 ppm to 1.5 ppm
    }
}
