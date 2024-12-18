package com.weather.web.controller.system;

import com.weather.web.service.SysRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * 注册
 * @author linkaixuan
 * @since 2024/4/9 20:53
 */
@RestController
@RequestMapping("/auth")
public class SysRegisterController {

    @Autowired
    private SysRegisterService registerService;


}
