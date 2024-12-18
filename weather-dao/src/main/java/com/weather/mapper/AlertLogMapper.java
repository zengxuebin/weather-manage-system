package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.AlertLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 预警日志 mapper层
 * @Author: linkaixuan
 * @Date: 2023/5/4 03:52
 */
@Mapper
public interface AlertLogMapper extends BaseMapper<AlertLog> {

}
