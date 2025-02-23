package com.weather.web.controller.alert;

import com.weather.common.constant.AlertResultConstants;
import com.weather.common.constant.AlertStatusConstants;
import com.weather.common.utils.ApiResult;
import com.weather.service.WeatherAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 忽略预警
 * @author linkaixuan
 * @since 2024/5/17 23:04
 */
@RestController
@RequestMapping("/alert/ignore")
public class AlertIgnoreController {

    @Autowired
    private WeatherAlertService weatherAlertService;

    /**
     * 忽略预警数据
     * @param alertIds 预警ID
     * @return 成功失败
     */
    @PostMapping("/handle")
    public ApiResult handleAlertIgnore(@RequestBody List<Long> alertIds) {
        weatherAlertService.updateAlertStatus(alertIds, AlertStatusConstants.PROCESS_IGNORED_ENDED);
        for (Long alertId : alertIds) {
            // 记录日志 已忽略
            weatherAlertService.recordAlertLog(alertId, AlertResultConstants.IGNORED);
        }
        return ApiResult.success();
    }
}
