package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.AlertPush;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警推送 mapper层
 * @author linkaixuan
 * @since 2024/5/4 03:55
 */
@Mapper
public interface AlertPushMapper extends BaseMapper<AlertPush> {
}
