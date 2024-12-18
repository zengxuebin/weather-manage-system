package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.SysDictData;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 字典 mapper层
 * @Author: linkaixuan
 * @Date: 2023/4/8 08:43
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {
}
