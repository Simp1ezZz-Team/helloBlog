package com.simple.helloblog.controller;

import com.simple.helloblog.service.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单控制器
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:14:24
 */
@RestController
@RequestMapping("/admin/menu")
@RequiredArgsConstructor
@Tag(name = "菜单模块", description = "菜单模块相关接口")
public class MenuController {
    /**
     * 菜单服务
     */
    private final MenuService menuService;
}
