package com.weather.web;

import com.weather.service.AlertPushService;
import org.jasypt.encryption.StringEncryptor;
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

    @Autowired
    private StringEncryptor encoder;

    @Test
    void testEncrypt() {
        System.out.println(encoder.encrypt("2025-01-05 10:13:00"));
    }
}
