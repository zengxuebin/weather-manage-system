package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.SysDictData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典 mapper层
 * @author linkaixuan
 * @since 2024/4/8 08:43
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {
}
