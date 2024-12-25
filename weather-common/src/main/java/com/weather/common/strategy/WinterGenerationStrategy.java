package com.weather.common.strategy;

import com.weather.domain.entity.WeatherData;

import java.util.Random;

/**
 * 冬天气象数据生成策略
 *
 * @author zengxuebin
 * @since 2024/12/24 21:14
 */
public class WinterGenerationStrategy  implements WeatherGenerationStrategy {
    private static final Random random = new Random();

    @Override
    public void generate(WeatherData weatherData) {
        weatherData.setWeather("阴天");
        weatherData.setTemperature(random.nextFloat() * (5 - (-10)) + (-10)); // -10°C to 5°C
        weatherData.setHumidity(random.nextFloat() * (70 - 30) + 30); // 30% to 70%
        weatherData.setPressure(random.nextFloat() * (1040 - 1000) + 1000); // 1000 hPa to 1040 hPa
        weatherData.setWindSpeed(random.nextFloat() * (15 - 5) + 5); // 5 m/s to 15 m/s
        weatherData.setWindDirection(random.nextInt(360)); // 0° to 360°
        weatherData.setPrecipitation(random.nextFloat() * (20) + 0); // 0 mm to 20 mm
        weatherData.setClouds(random.nextFloat() * (80 - 50) + 50); // 50% to 80%
        weatherData.setVisibility(random.nextInt(8000 - 3000) + 3000); // 3000m to 8000m
        weatherData.setAirQualityDesc("较差");
        weatherData.setAqi(random.nextInt(200 - 100) + 100); // 100 to 200
        weatherData.setPm25(random.nextInt(100 - 60) + 60); // 60 to 100
        weatherData.setPm10(random.nextInt(120 - 80) + 80); // 80 to 120
        weatherData.setNo2(random.nextInt(70 - 40) + 40); // 40 to 70
        weatherData.setSo2(random.nextInt(60 - 30) + 30); // 30 to 60
        weatherData.setO3(random.nextInt(120 - 70) + 70); // 70 to 120
        weatherData.setCo(random.nextFloat() + 1.5f); // 1.5 ppm to 2.5 ppm
    }
}
