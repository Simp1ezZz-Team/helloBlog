package com.simple.helloblog.controller;

import com.simple.helloblog.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色控制器
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:06:44
 */
@RestController
@RequestMapping("/admin/role")
@RequiredArgsConstructor
@Tag(name = "角色模块", description = "角色模块相关接口")
public class RoleController {
    /**
     * 角色服务
     */
    private final RoleService roleService;
}
