package com.weather.common.listener;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 版本校验
 *
 * @author zengxuebin
 * @since 2025/1/5 09:35
 */
@Component
public class ExpirationCheckListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final String APP_EXPIRATION_DATE = "1OZ8VVMd/xajeT9XT41SgPsm4K5K6uTpcAhz3Zfd1AMEFs+YsvVG4uhtNHE+eWnslL4X2lusrXZqcPPHhuzWXQ==";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private StringEncryptor encoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String expirationDateStr = "";
        try {
            expirationDateStr = encoder.decrypt(APP_EXPIRATION_DATE);
        } catch (Exception e) {
            System.exit(1);
        }

        if (expirationDateStr == null || expirationDateStr.isEmpty()) {
            System.err.println("Expiration date not configured.");
            return;
        }

        LocalDateTime expirationDate = LocalDateTime.parse(expirationDateStr, DATE_TIME_FORMATTER);
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(expirationDate)) {
            System.exit(1);
        }
    }
}
