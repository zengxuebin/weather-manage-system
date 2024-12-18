package com.weather.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weather.domain.entity.SysRole;

import java.util.Set;

/**
 * 角色 业务层
 * @author linkaixuan
 * @since 2024/4/2 12:02
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 查询角色权限
     * @param userId 用户id
     * @return 角色权限
     */
    Set<String> selectRolePermissionByUserId(Long userId);
}
