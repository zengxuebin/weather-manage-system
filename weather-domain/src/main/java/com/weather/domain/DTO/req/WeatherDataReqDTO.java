package com.weather.domain.DTO.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 天气数据查询请求参数
 *
 * @author zengxuebin
 * @since 2024/12/24 23:59
 */
@Data
public class WeatherDataReqDTO {

    /**
     * 站点
     */
    private Long stationNo;
    /**
     * 数据采集时间
     */
    private LocalDateTime dataCollectTime;
    /**
     * 天气描述
     */
    private String weather;
    /**
     * 温度
     */
    private Float temperature;
    /**
     * 湿度
     */
    private Float humidity;
    /**
     * 气压
     */
    private Float pressure;
    /**
     * 风速
     */
    private Float windSpeed;
    /**
     * 风向
     */
    private Integer windDirection;
    /**
     * 降水量
     */
    private Float precipitation;
    /**
     * 云量
     */
    private Float clouds;
    /**
     * 能见度
     */
    private Integer visibility;
    /**
     * 空气质量描述 优/良/轻度污染/重度污染
     */
    private String airQualityDesc;
    /**
     * 空气质量指标数值
     */
    private Integer aqi;
    /**
     * pm2.5浓度
     */
    private Integer pm25;
    /**
     * pm10浓度
     */
    private Integer pm10;
    /**
     * 二氧化氮浓度
     */
    private Integer no2;
    /**
     * 二氧化硫
     */
    private Integer so2;
    /**
     * 臭氧
     */
    private Integer o3;
    /**
     * 一氧化氮
     */
    private Float co;

}
