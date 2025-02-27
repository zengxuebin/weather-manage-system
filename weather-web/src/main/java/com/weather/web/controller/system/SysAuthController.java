package com.weather.web.controller.system;

import com.weather.common.constant.Constants;
import com.weather.common.utils.ApiResult;
import com.weather.common.utils.RedisCache;
import com.weather.common.utils.RedisKeyUtil;
import com.weather.common.utils.SecurityUtil;
import com.weather.domain.DTO.req.ChangePasswordReqDTO;
import com.weather.domain.entity.SysDept;
import com.weather.domain.entity.SysMenu;
import com.weather.domain.entity.SysUser;
import com.weather.domain.model.LoginBody;
import com.weather.domain.model.LoginUser;
import com.weather.service.SysDeptService;
import com.weather.service.SysMenuService;
import com.weather.service.SysUserService;
import com.weather.web.security.SecretUtil;
import com.weather.web.service.SysLoginService;
import com.weather.web.service.SysPasswordService;
import com.weather.web.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 登陆验证
 * @author linkaixuan
 * @since 2024/4/8 18:09
 */
@RestController
@RequestMapping("/auth")
public class SysAuthController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysDeptService deptService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 账号密码登陆
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody LoginBody loginBody) {
        String rawPassword = SecretUtil.desEncrypt(loginBody.getPassword());
        System.out.println(rawPassword);
        String token = loginService.login(loginBody.getUsername(), rawPassword,
                loginBody.getCode(), loginBody.getUuid());
        ApiResult apiResult = ApiResult.success();
        apiResult.put(Constants.TOKEN, token);
        return apiResult;
    }

    /**
     * 退出登陆
     * @return 结果
     */
    @PostMapping("/logout")
    public ApiResult logout() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        String token = loginUser.getToken();
        redisCache.deleteObject(RedisKeyUtil.getTokenKey(token));

        return ApiResult.success("退出成功");
    }

    @PostMapping("changePassword")
    public ApiResult changePassword(@RequestBody ChangePasswordReqDTO changePasswordReqDTO) {
        Long userId = SecurityUtil.getUserId();
        SysUser sysUser = sysUserService.getById(userId);
        boolean matches = passwordService.matches(sysUser, changePasswordReqDTO.getOldPassword());
        if (!matches) {
            return ApiResult.error("旧密码错误");
        }
        sysUser.setPassword(SecurityUtil.encryptPassword(changePasswordReqDTO.getNewPassword()));
        return sysUserService.updateById(sysUser) ? ApiResult.success("修改成功") : ApiResult.error("修改失败");
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @GetMapping("/getUserInfo")
    public ApiResult getUserInfo() {
        SysUser user = SecurityUtil.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        // 部门
        SysDept dept = deptService.getById(user.getDeptId());
        ApiResult apiResult = ApiResult.success();
        apiResult.put("user", user);
        apiResult.put("roles", roles);
        apiResult.put("permissions", permissions);
        apiResult.put("dept", dept);
        return apiResult;
    }

    /**
     * 获取路由信息
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public ApiResult getRouters() {
        Long userId = SecurityUtil.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        System.out.println(Arrays.toString(menuService.buildMenus(menus).toArray()));
        return ApiResult.success(menuService.buildMenus(menus));
    }
}
