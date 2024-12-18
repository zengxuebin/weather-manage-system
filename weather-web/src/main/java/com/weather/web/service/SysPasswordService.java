package com.weather.web.service;

import com.weather.common.constant.HttpStatus;
import com.weather.web.security.context.AuthenticationContextHolder;
import com.weather.common.utils.RedisCache;
import com.weather.common.utils.SecurityUtil;
import com.weather.common.exception.CustomException;
import com.weather.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @Description: 登陆密码
 * @Author: linkaixuan
 * @Date: 2023/3/31 20:09
 */
@Component
public class SysPasswordService {

    @Autowired
    private RedisCache redisCache;

    /**
     * 判断密码是否正确
     * @param user 用户信息
     * @param rawPassword 原始密码
     * @return true=正确 false=错误
     */
    public boolean matches(SysUser user, String rawPassword) {
        return SecurityUtil.matchesPassword(rawPassword, user.getPassword());
    }

    /**
     * 验证密码是否正确
     * @param user 用户信息
     */
    public void validate(SysUser user) {
        Authentication usernamePasswordAuthentication = AuthenticationContextHolder.getContext();
        String password = usernamePasswordAuthentication.getCredentials().toString();

        if (!matches(user, password)) {
            throw new CustomException(HttpStatus.ERROR, "用户名或密码错误");
        }
    }
}
