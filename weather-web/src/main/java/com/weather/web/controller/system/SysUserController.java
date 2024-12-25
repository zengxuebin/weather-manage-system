package com.weather.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weather.common.constant.UserConstants;
import com.weather.common.utils.ApiResult;
import com.weather.common.utils.SecurityUtil;
import com.weather.domain.DTO.req.SysUserQueryReqDTO;
import com.weather.domain.DTO.req.SysUserReqDTO;
import com.weather.domain.PageInfo;
import com.weather.domain.entity.SysUser;
import com.weather.service.SysUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 用户信息
 * @author linkaixuan
 * @since 2024/4/9 15:35
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 分页查询用户
     * @param pageInfo 分页条件
     * @return 数据
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<SysUserQueryReqDTO> pageInfo) {
        SysUserQueryReqDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.like(StringUtils.isNotBlank(entity.getUsername()), SysUser::getUsername, entity.getUsername())
                    .like(StringUtils.isNotBlank(entity.getPhone()), SysUser::getPhone, entity.getPhone())
                    .like(StringUtils.isNotBlank(entity.getEmail()), SysUser::getEmail, entity.getEmail())
                    .eq(StringUtils.isNotBlank(entity.getSex()), SysUser::getSex, entity.getSex())
                    .eq(entity.getDeptId() != null, SysUser::getDeptId, entity.getDeptId())
                    .eq(StringUtils.isNotBlank(entity.getStatus()), SysUser::getStatus, entity.getStatus());
        }
        IPage<SysUser> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ApiResult.success(userService.page(page, queryWrapper));
    }

    /**
     * 根据用户编号获取详情信息
     * @param userId 用户编号
     * @return 详情
     */
    @GetMapping("/{userId}")
    public ApiResult getUserInfo(@PathVariable Long userId) {
        return ApiResult.success(userService.getById(userId));
    }

    /**
     * 新增用户
     *
     * @param sysUserReqDTO 用户传输对象
     * @return 结果
     */
    @PostMapping("/add")
    public ApiResult addUser(@Validated @RequestBody SysUserReqDTO sysUserReqDTO) {
        if (StringUtils.isEmpty(sysUserReqDTO.getNickname())) {
            sysUserReqDTO.setNickname(sysUserReqDTO.getUsername());
        }
        SysUser user = new SysUser();
        BeanUtils.copyProperties(sysUserReqDTO, user);
        if (!userService.checkUsernameUnique(user)) {
            return ApiResult.warn("新增用户'" + user.getUsername() + "'失败，登陆账号已存在");
        } else if (!userService.checkPhoneUnique(user)) {
            return ApiResult.warn("新增用户'" + user.getUsername() + "'失败，手机号码已存在");
        } else if (!userService.checkEmailUnique(user)) {
            return ApiResult.warn("新增用户'" + user.getUsername() + "'失败，电子邮箱已存在");
        }
        user.setCreateBy(SecurityUtil.getLoginUser().getUsername());
        user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        return userService.save(user) ? ApiResult.success("创建成功") : ApiResult.error("创建失败，请联系系统管理员");
    }

    /**
     * 修改用户信息
     *
     * @param sysUserReqDTO 用户传输对象
     * @return 结果
     */
    @PostMapping("/update")
    public ApiResult updateUser(@Validated @RequestBody SysUserReqDTO sysUserReqDTO) {
        if (StringUtils.isEmpty(sysUserReqDTO.getNickname())) {
            sysUserReqDTO.setNickname(sysUserReqDTO.getUsername());
        }

        SysUser user = new SysUser();
        BeanUtils.copyProperties(sysUserReqDTO, user);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, user.getUsername());
        user.setUserId(userService.getOne(queryWrapper).getUserId());
        user.setUpdateBy(SecurityUtil.getLoginUser().getUsername());
        user.setUpdateTime(new Date());
        return userService.updateById(user) ? ApiResult.success("修改成功") : ApiResult.error("修改失败，请联系系统管理员");
    }

    /**
     * 修改用户状态
     * @param user 用户
     * @return 修改结果
     */
    @PostMapping("/changeStatus")
    public ApiResult changeStatus(@RequestBody SysUser user) {
        user.setUpdateBy(SecurityUtil.getLoginUser().getUsername());
        return userService.updateById(user) ? ApiResult.success("状态修改成功") : ApiResult.error("状态修改失败，请联系系统管理员");
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    @PostMapping("/delete")
    public ApiResult deleteUser(@RequestBody Long id) {
        if (SecurityUtil.isAdmin(id)) {
            return ApiResult.warn("管理员不能删除");
        }
        userService.removeById(id);
        return ApiResult.success();
    }

    /**
     * 批量删除用户
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ApiResult batchDeleteSysUser(@RequestBody List<Integer> ids) {
        userService.removeByIds(ids);
        return ApiResult.success();
    }

    /**
     * 获取在线用户列表
     * @return 在线用户
     */
    @PostMapping("/online/page")
    public ApiResult userOnline(@RequestBody PageInfo<SysUserQueryReqDTO> query) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getOnline, UserConstants.ONLINE);
        Page<SysUser> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(userService.page(page, queryWrapper));
    }
}
