package com.weather.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 字典分页查询条件
 * @Author: linkaixuan
 * @Date: 2024/4/8 09:59
 */
@Getter
@Setter
public class SysDictDataQuery {

    private String dictType;
    private String dictLabel;
    private String status;

}
