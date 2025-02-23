package com.weather.web.controller.alert;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weather.common.constant.AlertResultConstants;
import com.weather.common.constant.AlertStatusConstants;
import com.weather.common.utils.ApiResult;
import com.weather.domain.PageInfo;
import com.weather.domain.entity.SysUser;
import com.weather.domain.entity.WeatherAlert;
import com.weather.domain.model.WeatherAlertQuery;
import com.weather.service.AlertPushService;
import com.weather.service.WeatherAlertService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 推送预警
 * @author linkaixuan
 * @since 2024/5/18 10:44
 */
@RestController
@RequestMapping("/alert/push")
public class AlertPushController {

    @Autowired
    private WeatherAlertService weatherAlertService;

    @Autowired
    private AlertPushService alertPushService;

    /**
     * 查询预警信息（status = '1'）
     * @param query 查询条件
     * @return 结果集
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<WeatherAlertQuery> query) {

        // 把今天以前未处理的标记为已过期
        weatherAlertService.handleExpiredWeatherData(AlertStatusConstants.PENDING_PUSH);

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
            queryWrapper.eq(WeatherAlert::getAlertStatus, AlertStatusConstants.PENDING_PUSH);
            queryWrapper.orderByDesc(WeatherAlert::getTriggerTime);
        }

        Page<WeatherAlert> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(weatherAlertService.page(page, queryWrapper));
    }

    /**
     * 推送预警信息
     * @param alertIds 预警ID
     * @return 成功失败
     */
    @PostMapping("/handle")
    public ApiResult handleAlertRelease(@RequestBody List<Long> alertIds) {

        List<SysUser> usernameList = alertPushService.pushAlert(alertIds);
        System.out.println(usernameList);
        for (Long alertId : alertIds) {
            // 记录日志 已推送
            weatherAlertService.recordAlertLog(alertId, AlertResultConstants.PUSHED);
        }
        weatherAlertService.updateAlertStatus(alertIds, AlertStatusConstants.PENDING_CONFIRMATION);
        return ApiResult.warn("预警推送成功！", usernameList);
    }
}
