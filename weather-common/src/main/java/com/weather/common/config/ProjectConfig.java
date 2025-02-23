package com.weather.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目相关配置
 * @author linkaixuan
 * @since 2024/3/23 12:01
 */
@Component
@Data
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {

    /**
     * 项目名称
     */
    private String name;
    /**
     * 版本
     */
    private String version;
    /**
     * 版权年份
     */
    private String copyrightYear;
    /**
     * 路径
     */
    private static String profile;

    public static String getProfile() {
        return profile;
    }

    /**
     * 获取下载路径
     */
    public static String getDownPath() {
        return getProfile() + "/down/";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return getProfile() + "/avatar";
    }
}
