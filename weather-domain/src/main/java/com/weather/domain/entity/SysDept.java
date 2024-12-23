package com.weather.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.weather.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门表 sys_dept
 * @author linkaixuan
 * @since 2024/3/28 09:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDept extends BaseEntity {
    /**
     * 部门id
     */
    @TableId
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门负责人
     */
    private String leader;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 部门邮箱
     */
    private String email;
    /**
     * 部门状态 0正常 1停用
     */
    private String status;
    /**
     * 删除标志 0存在 1删除
     */
    private String delFlag;
    /**
     * 显示顺序
     */
    private Integer orderNum;
}
