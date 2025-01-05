package com.weather.web.controller.weather;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weather.common.utils.ApiResult;
import com.weather.domain.DTO.TempRangeDTO;
import com.weather.domain.DTO.WeatherHistoryDTO;
import com.weather.domain.VO.HistoryDataVO;
import com.weather.domain.entity.WeatherHistory;
import com.weather.domain.model.HistoryBody;
import com.weather.service.WeatherHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 历史天气
 *
 * @author linkaixuan
 * @since 2024/5/15 09:27
 */
@RestController
@RequestMapping("/history")
public class WeatherHistoryController {

    @Autowired
    private WeatherHistoryService historyService;

    @PostMapping("/statistics")
    public ApiResult statisticsHistory(@RequestBody HistoryBody historyBody) {
        // 统计量
        WeatherHistoryDTO historyDTO = historyService.statisticsWeatherHistory(historyBody.getNowCity(),
                historyBody.getStartDate(),
                historyBody.getEndDate());
        // 最高温度和最低温度
        List<Map<String, TempRangeDTO>> tempList = historyService.getTemperatureRange(historyBody.getNowCity(),
                historyBody.getStartDate(),
                historyBody.getEndDate());
        ApiResult apiResult = ApiResult.success();
        if (historyDTO == null) {
            historyDTO = new WeatherHistoryDTO();
        }
        apiResult.put("statistics", historyDTO);
        apiResult.put("tempList", tempList);

        LambdaQueryWrapper<WeatherHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WeatherHistory::getStationCity, historyBody.getNowCity());
        queryWrapper.ge(WeatherHistory::getHistoryDate, historyBody.getStartDate());
        queryWrapper.le(WeatherHistory::getHistoryDate, historyBody.getEndDate());
        List<WeatherHistory> historyList = historyService.list(queryWrapper);

        // 封装数据
        List<HistoryDataVO> historyDataVOS = new ArrayList<>();
        for (WeatherHistory weatherHistory : historyList) {
            HistoryDataVO historyDataVO = new HistoryDataVO();
            BeanUtils.copyProperties(weatherHistory, historyDataVO);
            historyDataVOS.add(historyDataVO);
        }
        apiResult.put("historyDataList", historyDataVOS);
        return apiResult;
    }
}
