package com.weather.domain.DTO.req;

import lombok.Data;

/**
 * 修改密码请求实体类
 *
 * @author zengxuebin
 * @since 2025/1/5 06:17
 */
@Data
public class ChangePasswordReqDTO {

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认新密码
     */
    private String checkNewPassword;
}
