package com.weather.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.weather.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 角色表 sys_role
 * @author linkaixuan
 * @since 2024/3/28 10:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity {

    /**
     * 角色序号
     */
    @TableId
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色字符串
     *
     */
    private String rolePerm;
    /**
     * 角色状态 0正常 1停用
     */
    private String status;
    /**
     * 删除标志 0存在 1删除
     */
    private String delFlag;
    /**
     * 角色菜单权限
     */
    @TableField(exist = false)
    private Set<String> permissions;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 备注
     */
    private String remark;
}
