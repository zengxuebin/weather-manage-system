package com.weather.web.service;

import com.weather.common.exception.CustomException;
import com.weather.common.utils.RedisCache;
import com.weather.common.utils.RedisKeyUtil;
import com.weather.common.utils.SecurityUtil;
import com.weather.domain.entity.SysUser;
import com.weather.domain.model.RegisterBody;
import com.weather.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注册校验
 * @author linkaixuan
 * @since 2024/4/3 08:25
 */
@Component
public class SysRegisterService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 用户注册
     * @param registerBody 用户信息
     * @return 注册结果
     */
    public String register(RegisterBody registerBody) {
        String username = registerBody.getUsername();
        String password = registerBody.getPassword();

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);

        validateCaptcha(registerBody.getCode(), registerBody.getUuid());

        if (userService.checkUsernameUnique(sysUser)) {
            sysUser.setNickname(username);
            sysUser.setPassword(SecurityUtil.encryptPassword(password));
            boolean regFlag = userService.save(sysUser);
            if (!regFlag) {
                return "注册失败，请联系系统管理人员";
            }
        } else {
            return "注册用户'" + username + "'失败，该账号已存在";
        }
        return "注册成功";
    }

    /**
     * 校验验证码
     * @param code 验证码
     * @param uuid 唯一标识
     */
    public void validateCaptcha(String code, String uuid) {
        String verifyKey = RedisKeyUtil.getCaptchaKey(uuid);
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new CustomException("验证码已过期");
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CustomException("验证码错误");
        }
    }
}
