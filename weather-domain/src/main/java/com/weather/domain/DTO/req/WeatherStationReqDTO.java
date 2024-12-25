package com.weather.domain.DTO.req;

import lombok.Data;

/**
 * 监测站查询请求参数
 *
 * @author zengxuebin
 * @since 2024/12/25 00:10
 */
@Data
public class WeatherStationReqDTO {

    /**
     * 站点编号
     */
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

}
