package com.weather.domain.VO;

import lombok.Data;

import java.util.List;

/**
 * 路由配置信息
 * @author linkaixuan
 * @since 2024/4/8 23:11
 */
@Data
public class RouterVO {

    /**
     * 路由名称
     */
    private String name;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 是否隐藏路由 true时路由不会在侧边栏出现
     */
    private boolean hidden;
    /**
     * 组件地址
     */
    private String component;
    /**
     * 其他元素
     */
    private MetaVO meta;
    /**
     * 子路由
     */
    private List<RouterVO> children;

}
