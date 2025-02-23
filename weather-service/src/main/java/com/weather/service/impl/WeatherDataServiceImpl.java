package com.weather.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weather.domain.entity.WeatherData;
import com.weather.mapper.WeatherDataMapper;
import com.weather.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 气象数据 业务实现层
 * @author linkaixuan
 * @since 2024/5/4 04:13
 */
@Service
public class WeatherDataServiceImpl extends ServiceImpl<WeatherDataMapper, WeatherData> implements WeatherDataService {

    @Autowired
    private WeatherDataMapper weatherDataMapper;


    /**
     * 采集数据保存至数据库
     *
     * @param stationNo 站点号
     * @param json      气象数据
     */
    @Override
    public void saveWeatherDataToDatabase(Long stationNo, String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        WeatherData weatherData = handleWeatherData(jsonObject);
        weatherData.setStationNo(stationNo);
        weatherDataMapper.insert(weatherData);
    }

    /**
     * 获取今日最新的气象数据
     *
     * @param today 今日日期
     * @return 气象数据
     */
    @Override
    public List<WeatherData> getLatestDataForEachStation(LocalDate today) {
        return weatherDataMapper.getLatestDataForEachStation(today);
    }

    public WeatherData handleWeatherData(JSONObject jsonObject) {
        JSONObject realtime = jsonObject.getJSONObject("result").getJSONObject("realtime");
        JSONObject airQuality = realtime.getJSONObject("air_quality");

        // 构建WeatherData对象
        WeatherData weatherData = new WeatherData();
        weatherData.setDataCollectTime(LocalDateTime.now());
        weatherData.setTemperature(realtime.getFloat("temperature"));
        weatherData.setWeather(realtime.getString("skycon"));
        weatherData.setHumidity(realtime.getFloat("humidity"));
        weatherData.setPressure(realtime.getFloat("pressure"));
        weatherData.setWindSpeed(realtime.getJSONObject("wind")
                .getFloat("speed"));
        weatherData.setWindDirection(realtime.getJSONObject("wind")
                .getInteger("direction") % 7);
        weatherData.setPrecipitation(realtime.getJSONObject("precipitation").
                getJSONObject("local")
                .getFloat("intensity"));
        weatherData.setClouds(realtime.getFloat("cloudrate"));
        weatherData.setVisibility(realtime.getInteger("visibility"));
        weatherData.setAirQualityDesc(airQuality
                .getJSONObject("description")
                .getString("chn"));
        weatherData.setAqi(airQuality
                .getJSONObject("aqi")
                .getInteger("chn"));
        weatherData.setPm25(airQuality.getInteger("pm25"));
        weatherData.setPm10(airQuality.getInteger("pm10"));
        weatherData.setNo2(airQuality.getInteger("no2"));
        weatherData.setSo2(airQuality.getInteger("so2"));
        weatherData.setO3(airQuality.getInteger("o3"));
        weatherData.setCo(airQuality.getFloat("co"));
        weatherData.setComfort(realtime.getJSONObject("life_index")
                .getJSONObject("comfort")
                .getString("desc"));
        return weatherData;
    }
}
