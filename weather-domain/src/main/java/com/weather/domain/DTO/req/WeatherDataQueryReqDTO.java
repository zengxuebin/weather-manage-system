package com.weather.domain.DTO.req;

import lombok.Data;

import java.time.LocalDate;

/**
 * 气象数据条件
 * @author linkaixuan
 * @since 2024/5/16 14:32
 */
@Data
public class WeatherDataQueryReqDTO {

    /**
     * 站台编号
     */
    private Long stationNo;

    /**
     * 风向
     */
    private Integer windDirection;

    /**
     * 监测时间
     */
    private LocalDate dataCollectDate;

}
