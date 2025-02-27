package com.weather.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.weather.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 * @author linkaixuan
 * @since 2024/3/24 19:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName
public class SysUser extends BaseEntity {

    /**
     * 用户id
     */
    @TableId
    private Long userId;
    /**
     * 部门
     */
    private Long deptId;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 账号状态
     */
    private String status;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;
    /**
     * 最后登陆ip
     */
    private String loginIp;
    /**
     * 最后登陆地点
     */
    private String loginLocation;
    /**
     * 是否在线(0在线 1离线)
     */
    private String online;
    /**
     * 最后登陆时间
     */
    private Date loginTime;
    /**
     * 角色对象
     */
    @TableField(exist = false)
    private List<SysRole> roles;

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
