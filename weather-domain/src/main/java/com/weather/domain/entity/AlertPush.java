package com.weather.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推送预警记录
 * @author linkaixuan
 * @since 2024/5/4 03:39
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_alert_push")
public class AlertPush {

    /**
     * 推送记录ID
     */
    @TableId
    private Integer pushId;
    /**
     * 预警记录
     */
    @TableField(exist = false)
    private WeatherAlert alert;
    /**
     * 推送方式
     */
    private String pushMethod;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;

}
