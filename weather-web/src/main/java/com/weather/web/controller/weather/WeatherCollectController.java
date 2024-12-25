package com.weather.web.controller.weather;

import com.weather.common.factory.WeatherDataFactory;
import com.weather.common.utils.ApiResult;
import com.weather.domain.entity.WeatherData;
import com.weather.domain.entity.WeatherStation;
import com.weather.service.WeatherDataService;
import com.weather.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 气象数据收集控制层
 *
 * @author zengxuebin
 * @since 2024/12/25 23:47
 */
@RestController
@RequestMapping("/weather/data")
public class WeatherCollectController {

    @Autowired
    private WeatherStationService weatherStationService;

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * 采集当前时间最新的气象数据
     *
     * @return 采集成功
     */
    @PostMapping("/collect")
    public ApiResult collectNowWeatherData() {
        List<WeatherStation> weatherStationList = weatherStationService.list();
        List<WeatherData> weatherDataList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (WeatherStation weatherStation : weatherStationList) {
            // 调用气象数据工厂 生成气象数据
            WeatherData weatherData = WeatherDataFactory.createWeatherData(weatherStation.getStationNo(), now);
            weatherData.setDataCollectTime(LocalDateTime.now());
            weatherDataList.add(weatherData);
        }
        weatherDataService.saveBatch(weatherDataList);
        return ApiResult.success();
    }

    /**
     * 采集指定日期的气象数据
     *
     * @param date 指定日期
     * @return 采集成功
     */
    @PostMapping("/collect/{date}")
    public ApiResult collectWeatherData(@PathVariable LocalDate date) {
        List<WeatherStation> weatherStationList = weatherStationService.list();
        List<WeatherData> weatherDataList = new ArrayList<>();
        for (WeatherStation weatherStation : weatherStationList) {
            WeatherData weatherData = WeatherDataFactory.createWeatherData(weatherStation.getStationNo(), date);
            // 获取当前时间（时分秒）
            LocalTime currentTime = LocalTime.now();

            // 将传入的日期与当前时间组合成 LocalDateTime
            LocalDateTime dataCollectTime = LocalDateTime.of(date, currentTime);

            // 设置收集时间
            weatherData.setDataCollectTime(dataCollectTime);
            weatherDataList.add(weatherData);
        }
        weatherDataService.saveBatch(weatherDataList);
        return ApiResult.success();
    }
}
