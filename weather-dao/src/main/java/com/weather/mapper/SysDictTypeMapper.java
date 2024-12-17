package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.SysDictType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 字典类型 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/4/8 08:45
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
}
