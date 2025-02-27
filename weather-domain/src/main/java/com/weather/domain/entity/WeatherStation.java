package com.weather.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 气象站
 * @author linkaixuan
 * @since 2024/5/4 02:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_weather_station")
public class WeatherStation {

    /**
     * 气象站点号
     */
    @TableId
    private Long stationNo;
    /**
     * 所在省份
     */
    private String stationProvince;
    /**
     * 所在市
     */
    private String stationCity;
    /**
     * 站点名称
     */
    private String stationName;
    /**
     * 站点类型
     */
    private String stationType;
    /**
     * 经度
     */
    private String stationLongitude;
    /**
     * 纬度
     */
    private String stationLatitude;
    /**
     * 高度
     */
    private String stationHeight;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标志(0存在 1删除)
     */
    private String delFlag;

}
