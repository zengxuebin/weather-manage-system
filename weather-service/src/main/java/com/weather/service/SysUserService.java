package com.weather.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weather.domain.entity.SysUser;

/**
 * 用户 业务层
 * @author linkaixuan
 * @since 2024/3/31 20:25
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 检验用户名是否唯一
     * @param user 用户
     * @return true=唯一 false=不唯一
     */
    boolean checkUsernameUnique(SysUser user);

    /**
     * 检验手机号是否唯一
     * @param user 用户
     * @return 结果
     */
    boolean checkPhoneUnique(SysUser user);

    /**
     * 检验验证码是否唯一
     * @param user 用户
     * @return 结果
     */
    boolean checkEmailUnique(SysUser user);
}
