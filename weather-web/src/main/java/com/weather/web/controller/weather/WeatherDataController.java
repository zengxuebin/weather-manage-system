package com.weather.web.controller.weather;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weather.common.utils.ApiResult;
import com.weather.domain.DTO.req.WeatherDataQueryReqDTO;
import com.weather.domain.DTO.req.WeatherDataReqDTO;
import com.weather.domain.PageInfo;
import com.weather.domain.entity.WeatherData;
import com.weather.service.WeatherDataService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 气象数据管理
 *
 * @author linkaixuan
 * @since 2024/5/16 12:10
 */
@RestController
@RequestMapping("/weather/data")
public class WeatherDataController {

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * 分页查询气象数据
     *
     * @param query 查询条件
     * @return 监测站
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<WeatherDataQueryReqDTO> query) {
        LambdaQueryWrapper<WeatherData> queryWrapper = new LambdaQueryWrapper<>();
        WeatherDataQueryReqDTO entity = query.getEntity();
        if (ObjectUtils.isNotEmpty(entity.getStationNo())) {
            queryWrapper.eq(WeatherData::getStationNo, entity.getStationNo());
        }
        if (ObjectUtils.isNotEmpty(entity.getWindDirection())) {
            queryWrapper.eq(WeatherData::getWindDirection, entity.getWindDirection());
        }
        if (ObjectUtils.isNotEmpty(entity.getDataCollectDate())) {
            LocalDateTime startOfDay = entity.getDataCollectDate().atStartOfDay(); // 当天的 00:00:00
            LocalDateTime endOfDay = entity.getDataCollectDate().atTime(LocalTime.MAX);
            queryWrapper.between(WeatherData::getDataCollectTime, startOfDay, endOfDay);
        }

        queryWrapper.orderByDesc(WeatherData::getDataCollectTime);
        Page<WeatherData> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(weatherDataService.page(page, queryWrapper));
    }

    /**
     * 新增气象数据
     *
     * @param weatherDataReqDTO 气象数据传输对象
     * @return 结果
     */
    @PostMapping("/add")
    public ApiResult addWeatherData(@Validated @RequestBody WeatherDataReqDTO weatherDataReqDTO) {
        WeatherData weatherData = new WeatherData();
        BeanUtils.copyProperties(weatherDataReqDTO, weatherData);
        return weatherDataService.save(weatherData) ? ApiResult.success("新建成功") : ApiResult.error("新建失败");
    }

    /**
     * 修改气象数据信息
     *
     * @param weatherDataReqDTO 气象数据传输对象
     * @return 结果
     */
    @PostMapping("/update")
    public ApiResult updateWeatherData(@Validated @RequestBody WeatherDataReqDTO weatherDataReqDTO) {
        WeatherData weatherData = new WeatherData();
        BeanUtils.copyProperties(weatherDataReqDTO, weatherData);
        return weatherDataService.updateById(weatherData) ? ApiResult.success("修改成功") : ApiResult.error("修改失败");
    }

    /**
     * 删除气象数据
     *
     * @param id 气象数据id
     * @return 结果
     */
    @PostMapping("/delete")
    public ApiResult deleteWeatherData(@RequestBody Long id) {
        weatherDataService.removeById(id);
        return ApiResult.success();
    }

    /**
     * 批量删除气象数据
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ApiResult batchDeleteWeatherData(@RequestBody List<Integer> ids) {
        weatherDataService.removeByIds(ids);
        return ApiResult.success();
    }

}
