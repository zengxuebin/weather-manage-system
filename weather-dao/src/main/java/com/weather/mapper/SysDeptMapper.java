package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 部门 mapper层
 * @Author: ZengXueBin
 * @Date: 2023/4/9 09:32
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
}
