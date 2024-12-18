package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.AlertSubscription;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警订阅 mapper层
 * @author linkaixuan
 * @since 2024/5/4 03:57
 */
@Mapper
public interface AlertSubscriptionMapper extends BaseMapper<AlertSubscription> {
}
