package com.weather.domain.model;

import jakarta.validation.constraints.Email;

/**
 * @Description: 用户注册对象
 * @Author: linkaixuan
 * @Date: 2024/4/3 08:35
 */
public class RegisterBody extends LoginBody {

    private String phone;
    private String sex;
    @Email
    private String email;

}
