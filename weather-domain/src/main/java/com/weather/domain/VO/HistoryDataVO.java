package com.weather.domain.VO;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.time.LocalDate;

/**
 * 历史数据VO
 *
 * @author zengxuebin
 * @since 2025/1/5 08:14
 */
@Data
public class HistoryDataVO {

    /**
     * 当天时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate historyDate;

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
