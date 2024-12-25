package com.weather.common.enums;

import lombok.Getter;

/**
 * 站点类型枚举类
 * 
 * @author zengxuebin
 * @since 2024/12/25 00:26
 */
@Getter
public enum StationTypeEnum {

    BASE_STATION("01", "基准站"),
    BASIC_STATION("02", "基本站"),
    GENERAL_STATION("03", "一般站");

    private final String code;
    private final String description;

    StationTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据代码获取站点类型描述
     *
     * @param code 站点类型代码
     * @return 对应的站点类型描述，如果找不到则返回 null
     */
    public static String getDescriptionByCode(String code) {
        for (StationTypeEnum stationType : StationTypeEnum.values()) {
            if (stationType.getCode().equals(code)) {
                return stationType.getDescription();
            }
        }
        return null;
    }

    /**
     * 根据描述获取站点类型代码
     *
     * @param description 站点类型描述
     * @return 对应的站点类型代码，如果找不到则返回 null
     */
    public static String getCodeByDescription(String description) {
        for (StationTypeEnum stationType : StationTypeEnum.values()) {
            if (stationType.getDescription().equals(description)) {
                return stationType.getCode();
            }
        }
        return null;
    }
}
