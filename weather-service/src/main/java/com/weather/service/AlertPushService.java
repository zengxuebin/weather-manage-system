package com.weather.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weather.domain.entity.AlertPush;
import com.weather.domain.entity.SysUser;

import java.util.List;

/**
 * @Description: 预警推送 业务层
 * @Author: linkaixuan
 * @Date: 2024/5/4 04:02
 */
public interface AlertPushService extends IService<AlertPush> {

    /**
     * 推送预警信息
     *
     * @param alertIds 预警信息ID
     * @return 结果
     */
    List<SysUser> pushAlert(List<Long> alertIds);
}
