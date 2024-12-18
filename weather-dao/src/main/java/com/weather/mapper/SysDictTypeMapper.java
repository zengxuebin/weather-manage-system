package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.SysDictType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典类型 mapper层
 * @author linkaixuan
 * @since 2024/4/8 08:45
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
}
