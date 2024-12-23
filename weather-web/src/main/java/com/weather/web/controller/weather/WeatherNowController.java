package com.weather.web.controller.weather;

import com.alibaba.fastjson2.JSONArray;
import com.weather.common.utils.ApiResult;
import com.weather.domain.entity.WeatherData;
import com.weather.web.service.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 目前天气
 * @author linkaixuan
 * @since 2024/5/16 08:25
 */
@RestController
@RequestMapping("/now")
public class WeatherNowController {

    @Autowired
    private NowService nowService;

    /**
     * 获取目前天气
     * @param location 经纬度
     * @return 结果
     */
    @GetMapping("/{location}")
    public ApiResult getNowWeather(@PathVariable String location) {
        RestTemplate restTemplate = new RestTemplate();
        String json = "";
        json = restTemplate.getForObject(
                "https://api.caiyunapp.com/v2.6/TAkhjf8d1nlSlspN/" + location + "/minutely",
                String.class);
        JSONArray jsonArray = nowService.handleTwoHourPrecipitation(json);
        ApiResult apiResult = ApiResult.success();
        apiResult.put("precipitation", jsonArray.get(0));
        apiResult.put("desc", jsonArray.get(1));
        json = restTemplate.getForObject("https://api.caiyunapp.com/v2.6/TAkhjf8d1nlSlspN/" + location + "/realtime",
                String.class);
        WeatherData weatherData =  nowService.handleNowWeather(json);
        apiResult.put("weatherNow", weatherData);
        return apiResult;
    }
}
