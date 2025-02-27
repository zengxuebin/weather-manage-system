package com.weather.web.controller.system;

import com.weather.common.config.ProjectConfig;
import com.weather.common.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 * @author linkaixuan
 * @since 2024/4/9 12:05
 */
@RestController
public class SysIndexController {

    @Autowired
    private ProjectConfig projectConfig;

    /**
     * 首页信息
     * @return 首页信息
     */
    @RequestMapping("/")
    public ApiResult index() {
        return ApiResult.success(projectConfig);
    }
}
