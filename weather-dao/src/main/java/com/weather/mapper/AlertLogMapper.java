package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.AlertLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警日志 mapper层
 * @author linkaixuan
 * @since 2024/5/4 03:52
 */
@Mapper
public interface AlertLogMapper extends BaseMapper<AlertLog> {

}
