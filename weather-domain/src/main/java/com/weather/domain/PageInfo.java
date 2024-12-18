package com.weather.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页查询条件 基类
 * @author linkaixuan
 * @since 2024/4/8 09:55
 */
@Getter
@Setter
public class PageInfo<T> implements Serializable {

    /**
     * 分页查询的页数
     */
    private Integer pageNum;
    /**
     * 分页查询的条数
     */
    private Integer pageSize;
    /**
     * 查询条件
     */
    private T entity;

}
