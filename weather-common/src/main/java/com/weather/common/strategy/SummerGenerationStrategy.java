package com.weather.common.strategy;

import com.weather.domain.entity.WeatherData;

import java.util.Random;

/**
 * 夏天气象数据生成策略
 *
 * @author zengxuebin
 * @since 2024/12/24 21:08
 */
public class SummerGenerationStrategy implements WeatherGenerationStrategy {
    private static final Random random = new Random();

    @Override
    public void generate(WeatherData weatherData) {
        weatherData.setWeather("多云");
        weatherData.setTemperature(random.nextFloat() * (40 - 25) + 25); // 25°C to 40°C
        weatherData.setHumidity(random.nextFloat() * (90 - 60) + 60); // 60% to 90%
        weatherData.setPressure(random.nextFloat() * (1020 - 980) + 980); // 980 hPa to 1020 hPa
        weatherData.setWindSpeed(random.nextFloat() * (12 - 3) + 3); // 3 m/s to 12 m/s
        weatherData.setWindDirection(random.nextInt(360)); // 0° to 360°
        weatherData.setPrecipitation(random.nextFloat() * (100 - 10) + 10); // 10 mm to 100 mm
        weatherData.setClouds(random.nextFloat() * (90 - 50) + 50); // 50% to 90%
        weatherData.setVisibility(random.nextInt(10000 - 3000) + 3000); // 3000m to 10000m
        weatherData.setAirQualityDesc("一般");
        weatherData.setAqi(random.nextInt(150 - 70) + 70); // 70 to 150
        weatherData.setPm25(random.nextInt(80 - 40) + 40); // 40 to 80
        weatherData.setPm10(random.nextInt(100 - 60) + 60); // 60 to 100
        weatherData.setNo2(random.nextInt(60 - 30) + 30); // 30 to 60
        weatherData.setSo2(random.nextInt(50 - 20) + 20); // 20 to 50
        weatherData.setO3(random.nextInt(150 - 80) + 80); // 80 to 150
        weatherData.setCo(random.nextFloat() + 1.0f); // 1.0 ppm to 2.0 ppm
    }
}
