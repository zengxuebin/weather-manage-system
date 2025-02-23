package com.weather.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weather.common.utils.ApiResult;
import com.weather.common.utils.SecurityUtil;
import com.weather.domain.PageInfo;
import com.weather.domain.entity.SysDictType;
import com.weather.domain.model.SysDictTypeQuery;
import com.weather.service.SysDictTypeService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典
 * @author linkaixuan
 * @since 2024/4/8 14:15
 */
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController {

    @Autowired
    private SysDictTypeService dictTypeService;

    /**
     * 分页查询字典
     * @param query 条件
     * @return 分页结果
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<SysDictTypeQuery> query) {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();
        SysDictTypeQuery queryEntity = query.getEntity();
        if (ObjectUtils.isNotEmpty(queryEntity)) {
            if (StringUtils.isNotBlank(queryEntity.getDictName())) {
                wrapper.eq(SysDictType::getDictName, queryEntity.getDictName());
            }
            if (StringUtils.isNotBlank(queryEntity.getStatus())) {
                wrapper.eq(SysDictType::getStatus, queryEntity.getStatus());
            }
            if (StringUtils.isNotBlank(queryEntity.getDictType())) {
                wrapper.eq(SysDictType::getDictType, queryEntity.getDictType());
            }
        }
        IPage<SysDictType> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(dictTypeService.page(page, wrapper));
    }

    /**
     * 查询字典类型明细
     * @param dictId 字典类型id
     * @return 字典类型数据
     */
    @GetMapping("/{dictId}")
    public ApiResult getInfoByDictId(@PathVariable Long dictId) {
        return ApiResult.success(dictTypeService.getById(dictId));
    }

    /**
     * 新增字典类型
     * @param dictType 字典类型
     * @return 结果
     */
    @PostMapping("/save")
    public ApiResult saveDictType(@Validated @RequestBody SysDictType dictType) {

        // 判断字典类型是否唯一
        if (!dictTypeService.checkDictTypeUnique(dictType)) {
            return ApiResult.warn("新增字典'" + dictType.getDictName() + "'失败，字典类型已存在");
        }

        String username = SecurityUtil.getLoginUser().getUsername();
        dictType.setCreateBy(username);
        dictTypeService.insertDictType(dictType);
        return ApiResult.success("创建成功");
    }

    /**
     * 修改字典类型
     * @param dictType 字典类型
     * @return 结果
     */
    @PostMapping("/edit")
    public ApiResult editDictType(@Validated @RequestBody SysDictType dictType) {

        // 判断字典类型是否唯一
        if (!dictTypeService.checkDictTypeUnique(dictType)) {
            return ApiResult.warn("修改字典'" + dictType.getDictName() + "'失败，字典类型已存在");
        }

        String username = SecurityUtil.getLoginUser().getUsername();
        dictType.setCreateBy(username);
        dictTypeService.updateDictType(dictType);
        return ApiResult.success("修改成功");
    }

    /**
     * 删除字典类型
     * @param dictIds 字典类型列表id
     * @return 结果
     */
    @PostMapping("/del")
    public ApiResult removeDictType(List<Long> dictIds) {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return ApiResult.success("删除成功");
    }

    /**
     * 重置字典缓存
     * @return 成功
     */
    @PostMapping("/refreshCache")
    public ApiResult refreshCache() {
        dictTypeService.resetDictCache();
        return ApiResult.success();
    }
}
