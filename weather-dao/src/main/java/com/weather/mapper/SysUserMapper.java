package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户类 mapper
 * @author linkaixuan
 * @since 2024/4/1 22:40
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据角色查找用户
     * @param roleId 角色ID
     * @return 用户列表
     */
    List<SysUser> findUsersWithRole(int roleId);
}
