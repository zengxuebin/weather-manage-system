package com.weather.domain.model;

import jakarta.validation.constraints.Email;

/**
 * 用户注册对象
 * @author linkaixuan
 * @since 2024/4/3 08:35
 */
public class RegisterBody extends LoginBody {

    private String phone;
    private String sex;
    @Email
    private String email;

}
