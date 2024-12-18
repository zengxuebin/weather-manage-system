package com.weather.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 字典类型分页查询条件
 * @author linkaixuan
 * @since 2024/4/8 14:21
 */
@Setter
@Getter
public class SysDictTypeQuery {

    private String dictName;
    private String status;
    private String dictType;

}
