package com.weather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weather.domain.entity.SysRole;
import com.weather.mapper.SysRoleMapper;
import com.weather.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色 业务实现层
 * @author linkaixuan
 * @since 2024/4/2 12:02
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询角色权限
     * @param userId 用户id
     * @return 角色权限
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectRolePermissionByUserId(userId);
        HashSet<String> permsSet = new HashSet<>();
        for (SysRole sysRole : sysRoles) {
            if (sysRole != null) {
                permsSet.addAll(Arrays.asList(sysRole.getRolePerm().trim().split(",")));
            }
        }
        return permsSet;
    }
}
