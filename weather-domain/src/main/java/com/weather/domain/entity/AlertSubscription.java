package com.weather.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户订阅
 * @author linkaixuan
 * @since 2024/5/4 03:35
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlertSubscription {

    /**
     * 订阅号
     */
    @TableId
    private Long subscriptionId;
    /**
     * 用户
     */
    @TableField(exist = false)
    private SysUser user;
    /**
     * 预警级别
     */
    private String alertLevel;
    /**
     * 是否订阅
     */
    private String isSubscription;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;
}
