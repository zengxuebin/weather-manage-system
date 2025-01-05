package com.weather.common.utils;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ExpirationChecker
 *
 * @author zengxuebin
 * @since 2025/1/5 10:05
 */
@Component
public class ExpirationChecker {

    private static final String encryptedExpirationDate = "1lcuxG/ik+N4ygmJes5iyDCFl9NmeBVHVd/rBTrWOWPXhbkAs86ZSikz3Z6uI/asJfxDc+tNppQs1gzW+rskpg==";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private StringEncryptor encryptor;

    public boolean isExpired() {
        String expirationDateStr = encryptor.decrypt(encryptedExpirationDate);
        if (expirationDateStr == null || expirationDateStr.isEmpty()) {
            return true;
        }

        LocalDateTime expirationDate = LocalDateTime.parse(expirationDateStr, DATE_TIME_FORMATTER);
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(expirationDate);
    }
}
