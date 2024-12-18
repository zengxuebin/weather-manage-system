package com.weather.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weather.common.constant.UserConstants;
import com.weather.domain.entity.SysDept;
import com.weather.mapper.SysDeptMapper;
import com.weather.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 部门 业务实现层
 * @author linkaixuan
 * @since 2024/4/9 09:33
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper deptMapper;

    /**
     * 校验部门是否唯一
     *
     * @param dept 部门
     * @return 结果
     */
    @Override
    public boolean checkDeptNameUnique(SysDept dept) {
        long deptId = dept.getDeptId() == null ? -1L : dept.getDeptId();
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDept::getDeptName, dept.getDeptName());

        SysDept sysDept = deptMapper.selectOne(wrapper);

        if (sysDept != null && sysDept.getDeptId() != deptId) {
            return UserConstants.NOT_UNIQUE;
        }

        return UserConstants.UNIQUE;
    }
}
