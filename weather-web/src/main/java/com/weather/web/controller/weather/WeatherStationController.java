package com.weather.web.controller.weather;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weather.common.enums.StationTypeEnum;
import com.weather.common.utils.ApiResult;
import com.weather.domain.DTO.StationCountDTO;
import com.weather.domain.DTO.StationTypeCountDTO;
import com.weather.domain.DTO.req.WeatherStationReqDTO;
import com.weather.domain.PageInfo;
import com.weather.domain.entity.WeatherStation;
import com.weather.domain.model.WeatherStationQuery;
import com.weather.service.WeatherStationService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 气象监测站
 * @author linkaixuan
 * @since 2024/5/14 13:57
 */
@RestController
@RequestMapping("weather/station")
public class WeatherStationController {

    @Autowired
    private WeatherStationService stationService;

    /**
     * 查询所有监测站
     * @return 所有监测站
     */
    @GetMapping("/list")
    public ApiResult queryAllStation() {
        List<WeatherStation> stationList = stationService.list();
        return ApiResult.success(stationList);
    }

    /**
     * 分页查询监测站
     * @param query 查询条件
     * @return 监测站
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<WeatherStationQuery> query) {
        System.out.println(query);
        LambdaQueryWrapper<WeatherStation> queryWrapper = new LambdaQueryWrapper<>();
        WeatherStationQuery entity = query.getEntity();
        if (ObjectUtils.isNotEmpty(entity)) {
            if (StringUtils.isNotBlank(entity.getStationProvince())) {
                queryWrapper.eq(WeatherStation::getStationProvince, entity.getStationProvince());
            }
            if (StringUtils.isNotBlank(entity.getStationCity())) {
                queryWrapper.eq(WeatherStation::getStationCity, entity.getStationCity());
            }
            if (StringUtils.isNotBlank(entity.getStationType())) {
                queryWrapper.eq(WeatherStation::getStationType, entity.getStationType());
            }
        }

        Page<WeatherStation> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(stationService.page(page, queryWrapper));
    }

    /**
     * 获取城市列表
     * @return 城市列表
     */
    @GetMapping("/city/list")
    public ApiResult queryAllCity() {
        List<Map<String, Object>> cityList = stationService.getAllCity();
        return ApiResult.success(cityList);
    }

    /**
     * 根据城市查询监测站
     * @param city 城市
     * @return 监测站列表
     */
    @GetMapping("/list/{city}")
    public ApiResult queryStationByCity(@PathVariable String city) {
        LambdaQueryWrapper<WeatherStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WeatherStation::getStationCity, city);
        List<WeatherStation> stationList = stationService.list(queryWrapper);
        return ApiResult.success(stationList);
    }

    /**
     * 统计监测站
     * @return 监测站统计量
     */
    @GetMapping("/count")
    public ApiResult countStation() {
        // 统计各个城市监测站个数
        List<StationCountDTO> countDTO = stationService.countStation();
        // 统计监测站类型个数
        List<StationTypeCountDTO> typeCountDTO = stationService.countStationType();
        ApiResult apiResult = ApiResult.success();
        apiResult.put("cityCount", countDTO);
        apiResult.put("typeCount", typeCountDTO);
        return apiResult;
    }

    /**
     * 新增气象站台
     *
     * @param weatherStationReqDTO 气象站台传输对象
     * @return 结果
     */
    @PostMapping("/add")
    public ApiResult addWeatherStation(@Validated @RequestBody WeatherStationReqDTO weatherStationReqDTO) {
        WeatherStation station = stationService.getById(weatherStationReqDTO.getStationNo());
        if (station != null) {
            return ApiResult.error("该气象站台已存在");
        }
        WeatherStation weatherStation = new WeatherStation();
        BeanUtils.copyProperties(weatherStationReqDTO, weatherStation);
        String stationTypeCode = StationTypeEnum.getCodeByDescription(weatherStation.getStationType());
        weatherStation.setStationNo(Long.valueOf(weatherStation.getStationNo() + stationTypeCode));
        return stationService.save(weatherStation) ? ApiResult.success("新建成功") : ApiResult.error("新建失败");
    }

    /**
     * 修改气象站台信息
     *
     * @param WeatherStationReqDTO 气象站台传输对象
     * @return 结果
     */
    @PostMapping("/update")
    public ApiResult updateWeatherStation(@Validated @RequestBody WeatherStationReqDTO WeatherStationReqDTO) {
        WeatherStation WeatherStation = new WeatherStation();
        BeanUtils.copyProperties(WeatherStationReqDTO, WeatherStation);
        return stationService.updateById(WeatherStation) ? ApiResult.success("修改成功") : ApiResult.error("修改失败");
    }

    /**
     * 删除气象站台
     *
     * @param id 气象站台id
     * @return 结果
     */
    @PostMapping("/delete")
    public ApiResult deleteWeatherStation(@RequestBody Long id) {
        stationService.removeById(id);
        return ApiResult.success();
    }

    /**
     * 批量删除气象站台
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ApiResult batchDeleteWeatherStation(@RequestBody List<Integer> ids) {
        stationService.removeByIds(ids);
        return ApiResult.success();
    }
}
