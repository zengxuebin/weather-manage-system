package com.weather.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 *
 * @author zengxuebin
 * @since 2025/1/4 15:46
 */
@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    private AsyncService asyncService;

    /**
     * 每隔10秒执行一次
     */
    @Scheduled(fixedRate = 10000)
    public void performTransfer() {
        log.info("定时任务触发：开始调用异步转存...");
        asyncService.asyncMigrateHistoryWeatherData();
        log.info("定时任务触发：异步转存已启动，请稍后查看结果。");
    }
}
