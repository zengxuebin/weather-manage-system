package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.AlertPush;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 预警推送 mapper层
 * @Author: linkaixuan
 * @Date: 2024/5/4 03:55
 */
@Mapper
public interface AlertPushMapper extends BaseMapper<AlertPush> {
}
