package com.weather.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 路由显示信息
 * @author linkaixuan
 * @since 2024/4/9 08:29
 */
@AllArgsConstructor
@Data
public class MetaVO {
    /**
     * 路由在侧边栏的标题
     */
    private String title;
    /**
     * 设置路由的图标
     */
    private String icon;
}
