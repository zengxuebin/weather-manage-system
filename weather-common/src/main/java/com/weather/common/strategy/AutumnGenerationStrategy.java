package com.weather.common.strategy;

import com.weather.domain.entity.WeatherData;

import java.util.Random;

/**
 * 秋天气象数据生成策略
 *
 * @author zengxuebin
 * @since 2024/12/24 21:12
 */
public class AutumnGenerationStrategy implements WeatherGenerationStrategy {
    private static final Random random = new Random();

    @Override
    public void generate(WeatherData weatherData) {
        weatherData.setWeather("晴朗");
        weatherData.setTemperature(random.nextFloat() * (25 - 10) + 10); // 10°C to 25°C
        weatherData.setHumidity(random.nextFloat() * (70 - 40) + 40); // 40% to 70%
        weatherData.setPressure(random.nextFloat() * (1020 - 980) + 980); // 980 hPa to 1020 hPa
        weatherData.setWindSpeed(random.nextFloat() * (8 - 2) + 2); // 2 m/s to 8 m/s
        weatherData.setWindDirection(random.nextInt(360)); // 0° to 360°
        weatherData.setPrecipitation(random.nextFloat() * (30) + 0); // 0 mm to 30 mm
        weatherData.setClouds(random.nextFloat() * (60 - 30) + 30); // 30% to 60%
        weatherData.setVisibility(random.nextInt(10000 - 5000) + 5000); // 5000m to 10000m
        weatherData.setAirQualityDesc("良好");
        weatherData.setAqi(random.nextInt(80 - 40) + 40); // 40 to 80
        weatherData.setPm25(random.nextInt(40 - 20) + 20); // 20 to 40
        weatherData.setPm10(random.nextInt(60 - 30) + 30); // 30 to 60
        weatherData.setNo2(random.nextInt(30 - 15) + 15); // 15 to 30
        weatherData.setSo2(random.nextInt(20 - 10) + 10); // 10 to 20
        weatherData.setO3(random.nextInt(100 - 50) + 50); // 50 to 100
        weatherData.setCo(random.nextFloat() * (1.2f - 0.6f) + 0.6f); // 0.6 ppm to 1.2 ppm
    }
}
