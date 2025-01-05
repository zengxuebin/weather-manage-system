package com.weather.web.controller;

import com.weather.common.utils.ApiResult;
import com.weather.service.SysUserService;
import com.weather.service.WeatherDataService;
import com.weather.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制层
 *
 * @author zengxuebin
 * @since 2025/1/4 16:09
 */
@RestController
public class HomeController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private WeatherStationService stationService;

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * 首页信息
     * @return 首页信息
     */
    @GetMapping("/home")
    public ApiResult home() {
        ApiResult apiResult = ApiResult.success();
        apiResult.put("userCount", sysUserService.count());
        apiResult.put("stationCount", stationService.count());
        apiResult.put("weatherDataCount", weatherDataService.count());
        return apiResult;
    }
}
