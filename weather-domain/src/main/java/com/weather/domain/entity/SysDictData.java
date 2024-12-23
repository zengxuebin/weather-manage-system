package com.weather.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.weather.domain.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据表 sys_dict_data
 * @author linkaixuan
 * @since 2024/3/24 20:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictData extends BaseEntity {

    /**
     * 字典编码
     */
    @TableId
    private Long dictCode;
    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不能为空")
    private String dictLabel;
    /**
     * 字典值
     */
    @NotBlank(message = "字典值不能为空")
    private String dictValue;
    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    private String dictType;
    /**
     * 字典排序
     */
    private Long orderNum;
    /**
     * 状态
     */
    private String status;
    /**
     * 是否默认
     */
    private String isDefault;

}
