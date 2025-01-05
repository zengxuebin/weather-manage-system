package com.weather.domain.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 历史天气统计表
 * @author linkaixuan
 * @since 2024/5/15 09:19
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_weather_history")
public class WeatherHistory {
    /**
     * 历史天气ID
     */
    @TableId
    private Long historyDataId;
    /**
     * 站点所在城市
     */
    private String stationCity;
    /**
     * 当天时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate historyDate;
    /**
     * 当天最高温度
     */
    private Float tempMax;
    /**
     * 当天最低温度
     */
    private Float tempMin;
    /**
     * 当天降水量
     */
    private Double precipitation;
    /**
     * 当天风速
     */
    private Double windSpeed;
    /**
     * 当天能见度
     */
    private Double visibility;

}
