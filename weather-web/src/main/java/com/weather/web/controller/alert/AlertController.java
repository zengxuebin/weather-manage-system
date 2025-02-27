package com.weather.web.controller.alert;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weather.common.constant.AlertStatusConstants;
import com.weather.common.utils.ApiResult;
import com.weather.domain.PageInfo;
import com.weather.domain.entity.AlertLog;
import com.weather.domain.entity.WeatherAlert;
import com.weather.domain.model.WeatherAlertQuery;
import com.weather.service.AlertLogService;
import com.weather.service.WeatherAlertService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预警
 * @author linkaixuan
 * @since 2024/5/18 09:24
 */
@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private WeatherAlertService weatherAlertService;
    @Autowired
    private AlertLogService alertLogService;

    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<WeatherAlertQuery> query) {
        LambdaQueryWrapper<WeatherAlert> queryWrapper = new LambdaQueryWrapper<>();
        WeatherAlertQuery entity = query.getEntity();
        if (ObjectUtils.isNotEmpty(entity)) {
            if (StringUtils.isNotBlank(entity.getAlertTitle())) {
                queryWrapper.like(WeatherAlert::getAlertTitle, entity.getAlertTitle());
            }
            if (StringUtils.isNotBlank(entity.getAlertType())) {
                queryWrapper.eq(WeatherAlert::getAlertType, entity.getAlertType());
            }
            if (StringUtils.isNotBlank(entity.getAlertLevel())) {
                queryWrapper.eq(WeatherAlert::getAlertLevel, entity.getAlertLevel());
            }
            if (StringUtils.isNotBlank(entity.getAlertRuleId())) {
                queryWrapper.eq(WeatherAlert::getAlertRuleId, entity.getAlertRuleId());
            }
            if (StringUtils.isNotBlank(entity.getAlertSource())) {
                queryWrapper.eq(WeatherAlert::getAlertSource, entity.getAlertSource());
            }
            if (StringUtils.isNotBlank(entity.getAlertAreaId())) {
                queryWrapper.eq(WeatherAlert::getAlertAreaId, entity.getAlertAreaId());
            }
            if (StringUtils.isNotBlank(entity.getAlertStatus())) {
                queryWrapper.eq(WeatherAlert::getAlertStatus, entity.getAlertStatus());
            }
            queryWrapper.orderByDesc(WeatherAlert::getTriggerTime);
        }

        Page<WeatherAlert> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(weatherAlertService.page(page, queryWrapper));
    }

    /**
     * 获取已推送的预警数据
     * @return 已推送
     */
    @GetMapping("/pushed/list")
    public ApiResult getListPushedAlert() {
        LambdaQueryWrapper<WeatherAlert> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WeatherAlert::getAlertStatus, AlertStatusConstants.PENDING_CONFIRMATION)
                .orderByDesc(WeatherAlert::getTriggerTime);
        List<WeatherAlert> weatherAlerts = weatherAlertService.list(queryWrapper);
        return ApiResult.success(weatherAlerts);
    }

    /**
     * 查询预警日志
     * @param alertId 预警ID
     * @return 预警日志
     */
    @GetMapping("/log/{alertId}")
    public ApiResult getAlertLogByAlertId(@PathVariable String alertId) {
        LambdaQueryWrapper<AlertLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AlertLog::getAlertId, alertId);
        queryWrapper.orderByAsc(AlertLog::getHandleTime);
        List<AlertLog> alertLogList = alertLogService.list(queryWrapper);
        return ApiResult.success(alertLogList);
    }
}
