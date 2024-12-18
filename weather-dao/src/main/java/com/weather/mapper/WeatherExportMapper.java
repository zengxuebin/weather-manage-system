package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.WeatherExport;
import org.apache.ibatis.annotations.Mapper;

/**
 * 气象数据导出 mapper层
 * @author linkaixuan
 * @since 2024/5/4 03:59
 */
@Mapper
public interface WeatherExportMapper extends BaseMapper<WeatherExport> {
}
