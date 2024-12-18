package com.weather.common.exception;

import com.weather.common.constant.HttpStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 * @author linkaixuan
 * @since 2024/3/30 15:39
 */
@Getter
@Setter
public class CustomException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException(String message) {
        this.code = HttpStatus.ERROR;
        this.message = message;
    }
}
