package com.weather.domain.DTO.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户分页查询条件
 * @author linkaixuan
 * @since 2024/4/9 16:34
 */
@Getter
@Setter
public class SysUserQueryReqDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 部门
     */
    private Long deptId;

    /**
     * 状态
     */
    private String status;

}
