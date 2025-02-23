package com.weather.web;

import com.weather.common.utils.SecurityUtil;
import com.weather.service.AlertPushService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@EnableConfigurationProperties
class WeatherApplicationTests {


    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        Object o = restTemplate.getForObject(
                "https://api.caiyunapp.com/v2.6/TAkhjf8d1nlSlspN/115.892151,28.676493/hourly?hourlysteps=24",
                String.class);
        System.out.println(o);
    }

    @Autowired
    private AlertPushService alertPushService;

    @Test
    void testEncrypt() {
        System.out.println(SecurityUtil.encryptPassword("test"));
    }
}
