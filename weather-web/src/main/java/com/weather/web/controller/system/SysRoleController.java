package com.weather.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weather.common.utils.ApiResult;
import com.weather.domain.PageInfo;
import com.weather.domain.entity.SysRole;
import com.weather.domain.model.SysRoleQuery;
import com.weather.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 角色
 * @Author: ZengXueBin
 * @Date: 2023/5/14 21:53
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<SysRoleQuery> query) {
        Page<SysRole> page = new Page<>(query.getPageNum(), query.getPageSize());
        return ApiResult.success(roleService.page(page));
    }
}
