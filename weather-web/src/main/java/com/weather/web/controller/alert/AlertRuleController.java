package com.weather.web.controller.alert;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weather.common.utils.ApiResult;
import com.weather.domain.PageInfo;
import com.weather.domain.entity.AlertRule;
import com.weather.domain.model.AlertRuleQuery;
import com.weather.service.AlertRuleService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 预警规则
 * @author linkaixuan
 * @since 2024/5/17 00:58
 */
@RestController
@RequestMapping("/alert/rule")
public class AlertRuleController {

    @Autowired
    private AlertRuleService alertRuleService;

    /**
     * 查询预警规则分页信息
     *
     * @param query 查询条件
     * @return 分页预警规则
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<AlertRuleQuery> query) {
        LambdaQueryWrapper<AlertRule> queryWrapper = new LambdaQueryWrapper<>();
        AlertRuleQuery entity = query.getEntity();
        if (ObjectUtils.isNotEmpty(entity)) {
            if (StringUtils.isNotBlank(entity.getRuleName())) {
                queryWrapper.like(AlertRule::getRuleName, entity.getRuleName());
            }
            if (StringUtils.isNotBlank(entity.getAlertLevel())) {
                queryWrapper.eq(AlertRule::getAlertLevel, entity.getAlertLevel());
            }
            if (StringUtils.isNotBlank(entity.getMetric())) {
                queryWrapper.like(AlertRule::getMetric, entity.getMetric());
            }
        }

        Page<AlertRule> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(alertRuleService.page(page, queryWrapper));
    }

    /**
     * 预警规则列表
     *
     * @return 预警规则
     */
    @GetMapping("/list")
    public ApiResult queryListByRuleId() {
        return ApiResult.success(alertRuleService.list());
    }
}
