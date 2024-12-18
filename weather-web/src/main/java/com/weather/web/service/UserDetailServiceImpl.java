package com.weather.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weather.common.enums.UserStatusEnum;
import com.weather.common.exception.CustomException;
import com.weather.domain.entity.SysDept;
import com.weather.domain.entity.SysUser;
import com.weather.domain.model.LoginUser;
import com.weather.service.SysDeptService;
import com.weather.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户验证处理
 * @Author: linkaixuan
 * @Date: 2023/4/3 08:59
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysDeptService deptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysUser::getUsername, username);
        SysUser user = userService.getOne(userLambdaQueryWrapper);

        if (user == null) {
            throw new CustomException("登陆用户：" + username + "不存在");
        } else if (UserStatusEnum.DELETED.getCode().equals(user.getDelFlag())) {
            throw new CustomException("对不起，您的账号：" + username + "已被删除");
        } else if (UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new CustomException("对不起，您的账号：" + username + "已停用");
        }

        SysDept dept = deptService.getById(user.getDeptId());

        passwordService.validate(user);
        return new LoginUser(user.getUserId(), dept, user, permissionService.getMenuPermission(user));
    }

}
