package com.weather.web.async;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weather.common.enums.HandledStatusEnum;
import com.weather.domain.entity.WeatherData;
import com.weather.domain.entity.WeatherHistory;
import com.weather.domain.entity.WeatherStation;
import com.weather.service.WeatherDataService;
import com.weather.service.WeatherHistoryService;
import com.weather.service.WeatherStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 异步服务类
 *
 * @author zengxuebin
 * @since 2025/1/4 15:42
 */
@Service
public class AsyncService {

    private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private WeatherHistoryService historyService;

    @Autowired
    private WeatherStationService stationService;

    /**
     * 异步转存历史气象数据
     */
    @Async
    public void asyncMigrateHistoryWeatherData() {
        log.info("开始异步转存历史气象数据...");
        LambdaQueryWrapper<WeatherData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WeatherData::getIsHandled, HandledStatusEnum.UNHANDLED.getCode());
        List<WeatherData> weatherDataList = weatherDataService.list(queryWrapper);
        if (weatherDataList.isEmpty()) {
            return;
        }

        // 按照 station_no 和日期分组
        Map<String, List<WeatherData>> groupedByStationAndDate = weatherDataList.stream()
                .collect(Collectors.groupingBy(data -> data.getStationNo() + "#" + data.getDataCollectTime().toLocalDate()));

        // 查询station_city
        Map<Long, String> stationCityMap = stationService.list().stream()
                .collect(Collectors.toMap(WeatherStation::getStationNo, WeatherStation::getStationCity));

        // 历史数据列表
        List<WeatherHistory> historyDataList = new ArrayList<>();
        // 更新数据列表
        List<WeatherData> updateDataList = new ArrayList<>();
        for (Map.Entry<String, List<WeatherData>> entry : groupedByStationAndDate.entrySet()) {
            String key = entry.getKey();
            List<WeatherData> dataList = entry.getValue();

            // 提取 station_city（假设 station_no 对应 city）
            String stationCity = stationCityMap.get(Long.valueOf(key.split("#")[0]));

            // 计算最大温度、最小温度等
            Float tempMax = dataList.stream()
                    .map(WeatherData::getTemperature)
                    .max(Float::compareTo)
                    .orElse(0F);
            Float tempMin = dataList.stream()
                    .map(WeatherData::getTemperature)
                    .min(Float::compareTo)
                    .orElse(0F);
            Double avgPrecipitation = dataList.stream()
                    .mapToDouble(WeatherData::getPrecipitation)
                    .average()
                    .orElse(0.0f);

            Double avgWindSpeed = dataList.stream()
                    .mapToDouble(WeatherData::getWindSpeed)
                    .average()
                    .orElse(0.0f);

            Double avgVisibility = dataList.stream()
                    .mapToDouble(WeatherData::getVisibility)
                    .average()
                    .orElse(0.0f);

            String weather = dataList.get(0).getWeather(); // 假设天气类型相同

            // 构建历史数据对象
            WeatherHistory historyData = new WeatherHistory();
            historyData.setStationCity(stationCity);
            historyData.setHistoryDate(LocalDate.parse(key.split("#")[1]));
            historyData.setTempMax(tempMax);
            historyData.setTempMin(tempMin);
            historyData.setPrecipitation(avgPrecipitation);
            historyData.setWindSpeed(avgWindSpeed);
            historyData.setVisibility(avgVisibility);

            // 插入历史数据表
            historyDataList.add(historyData);

            // 更新原始数据的 is_handled 标志
            dataList.forEach(data -> {
                data.setIsHandled(HandledStatusEnum.HANDLED.getCode());
                updateDataList.add(data);
            });
        }
        historyService.saveBatch(historyDataList);
        weatherDataService.updateBatchById(updateDataList);

        log.info("异步转存历史气象数据完成！");
    }
}
