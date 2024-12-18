package com.weather.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单查询条件
 * @author linkaixuan
 * @since 2024/5/14 21:10
 */
@Getter
@Setter
public class SysMenuQuery {

    private String menuName;
    private String menuType;
    private String visible;
}
