package com.weather.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weather.domain.entity.SysDept;

/**
 * 部门 业务层
 * @author linkaixuan
 * @since 2024/4/9 09:33
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 校验部门是否唯一
     * @param dept 部门
     * @return 结果
     */
    boolean checkDeptNameUnique(SysDept dept);
}
