package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门 mapper层
 * @author linkaixuan
 * @since 2024/4/9 09:32
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
}
