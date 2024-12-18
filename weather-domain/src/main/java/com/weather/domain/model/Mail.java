package com.weather.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Mail 实体
 * @author linkaixuan
 * @since 2024/5/20 01:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail implements Serializable {

    /**
     * 邮件接收方
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

    public void setTos(String... recipients) {
        this.tos = recipients;
    }
}
