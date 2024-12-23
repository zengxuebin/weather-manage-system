package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.AlertRule;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警规则 mapper层
 * @author linkaixuan
 * @since 2024/5/4 03:56
 */
@Mapper
public interface AlertRuleMapper extends BaseMapper<AlertRule> {
}
