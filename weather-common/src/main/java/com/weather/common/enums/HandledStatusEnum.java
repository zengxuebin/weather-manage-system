package com.weather.common.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * 处理状态枚举类
 *
 * @author CaoLongHui
 * @since 2025/1/4 17:49
 */
@Getter
public enum HandledStatusEnum {

    HANDLED("0", "已处理"),
    UNHANDLED("1", "未处理");

    private final String code;
    private final String description;

    HandledStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescriptionByCode(String code) {
        for (HandledStatusEnum value : HandledStatusEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value.getDescription();
            }
        }
        return null;
    }
}
