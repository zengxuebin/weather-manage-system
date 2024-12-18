package com.weather.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门分页查询条件
 * @author linkaixuan
 * @since 2024/4/9 09:43
 */
@Getter
@Setter
public class SysDeptQuery {

    private String deptName;
    private String leader;
    private String status;

}
