package com.weather.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weather.domain.entity.SysDictData;
import com.weather.domain.entity.SysDictType;

import java.util.List;

/**
 * @Description: 字典类型 业务层
 * @Author: linkaixuan
 * @Date: 2024/4/8 08:46
 */
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 根据字典类型查询数据
     * @param dictType 字典类型
     * @return 字典数据集合
     */
    List<SysDictData> selectDictDataByDictType(String dictType);

    /**
     * 新增保存字典类型
     *
     * @param dictType 字典类型
     */
    void insertDictType(SysDictType dictType);

    /**
     * 修改字典类型
     *
     * @param dictType 字典类型
     */
    void updateDictType(SysDictType dictType);

    /**
     * 删除字典类型
     *
     * @param dictIds 类型id
     */
    void deleteDictTypeByIds(List<Long> dictIds);

    /**
     * 重置字段缓存数据
     */
    void resetDictCache();

    /**
     * 校验字典类型是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    boolean checkDictTypeUnique(SysDictType dictType);
}
