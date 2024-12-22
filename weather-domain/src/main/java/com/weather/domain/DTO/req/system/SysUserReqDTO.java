package com.weather.domain.DTO.req.system;

import lombok.Data;

/**
 * 系统用户请求传输对象
 *
 * @author zengxuebin
 * @since 2024/12/22 14:21
 */
@Data
public class SysUserReqDTO {

    /**
     * 所在部门 100 数据监控中心 101用户中心
     */
    private Long deptId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户性别 0男 1女
     */
    private String sex;

    /**
     * 账号状态 0-正常 1-停用
     */
    private String status;

}
