package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 菜单 mapper层
 * @Author: linkaixuan
 * @Date: 2024/4/2 12:04
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> selectMenuPermsByUserId(Long userId);

    List<String> selectMenuPermsByRoleId(Long roleId);

    List<SysMenu> selectMenuTreeByUserId(Long userId);
}
