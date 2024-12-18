package com.weather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weather.domain.entity.AlertRecipient;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警接收 mapper层
 * @author linkaixuan
 * @since 2024/5/4 03:56
 */
@Mapper
public interface AlertRecipientMapper extends BaseMapper<AlertRecipient> {
}
