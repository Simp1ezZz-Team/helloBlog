package com.simple.helloblog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.simple.helloblog.model.dto.MenuDTO;
import com.simple.helloblog.model.vo.MenuTree;
import com.simple.helloblog.model.vo.MenuVO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.Result;
import com.simple.helloblog.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 查询菜单列表
     *
     * @param menuDTO 菜单 DTO
     * @return {@link PageResult}<{@link MenuVO}>
     */
    @GetMapping("/list")
    @SaCheckPermission("system:menu:list")
    @Operation(summary = "查询菜单列表", description = "查询菜单列表")
    public Result<PageResult<MenuVO>> listMenuVO(@Validated MenuDTO menuDTO) {
        return Result.success(menuService.listMenuVO(menuDTO));
    }

    /**
     * 添加菜单
     *
     * @param menuDTO 菜单 DTO
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping
    @SaCheckPermission("system:menu:add")
    @Operation(summary = "添加菜单", description = "添加菜单")
    public Result<Object> addMenu(@RequestBody @Validated MenuDTO menuDTO) {
        menuService.addMenu(menuDTO);
        return Result.success();
    }

    /**
     * 更新菜单
     *
     * @param menuDTO 菜单 DTO
     * @return {@link Result}<{@link ?}>
     */
    @PatchMapping
    @SaCheckPermission("system:menu:update")
    @Operation(summary = "更新菜单", description = "更新菜单")
    public Result<Object> updateMenu(@RequestBody @Validated MenuDTO menuDTO) {
        menuService.updateMenu(menuDTO);
        return Result.success();
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单Id
     * @return {@link Result}<{@link ?}>
     */
    @DeleteMapping("/{menuId}")
    @SaCheckPermission("system:menu:delete")
    @Operation(summary = "删除菜单", description = "删除菜单")
    public Result<Object> deleteMenu(@PathVariable("menuId") Integer menuId) {
        menuService.deleteMenu(menuId);
        return Result.success();
    }

    /**
     * 获取菜单树
     *
     * @return {@link Result}<{@link List}<{@link MenuTree}>>
     */
    @GetMapping("/tree")
    @SaCheckPermission("system:menu:tree")
    @Operation(summary = "获取菜单树", description = "获取菜单树")
    public Result<List<MenuTree>> listMenuTree() {
        return Result.success(menuService.listMenuTree());
    }
}
