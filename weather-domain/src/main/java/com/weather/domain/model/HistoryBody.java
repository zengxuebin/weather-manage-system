package com.weather.domain.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 历史天气统计条件
 * @author linkaixuan
 * @since 2024/5/15 09:31
 */
@Data
public class HistoryBody {

    private String nowCity;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
}
