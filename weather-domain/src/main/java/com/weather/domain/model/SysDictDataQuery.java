package com.weather.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 字典分页查询条件
 * @author linkaixuan
 * @since 2024/4/8 09:59
 */
@Getter
@Setter
public class SysDictDataQuery {

    private String dictType;
    private String dictLabel;
    private String status;

}
