package com.weather.common.utils;

import com.weather.common.constant.CacheConstants;

/**
 * redis key工具类
 * @author linkaixuan
 * @since 2024/4/8 17:54
 */
public class RedisKeyUtil {
    private RedisKeyUtil() {
    }

    /**
     * 获取字典key
     * @param configKey key
     * @return 缓存key
     */
    public static String getDictKey(String configKey) {
        return CacheConstants.SYS_DICT_KEY + configKey;
    }

    /**
     * 获取token key
     * @param uuid uuid
     * @return token在redis中的key
     */
    public static String getTokenKey(String uuid) {
        return CacheConstants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 获取captcha key
     * @param uuid uuid
     * @return token在redis中的key
     */
    public static String getCaptchaKey(String uuid) {
        return CacheConstants.CAPTCHA_CODE_KEY + uuid;
    }

}
