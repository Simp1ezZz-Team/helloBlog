package com.simple.helloblog.controller;

import com.simple.helloblog.model.dto.MenuDTO;
import com.simple.helloblog.model.vo.MenuOption;
import com.simple.helloblog.model.vo.MenuTree;
import com.simple.helloblog.model.vo.MenuVO;
import com.simple.helloblog.model.vo.Result;
import com.simple.helloblog.service.MenuService;
import com.simple.helloblog.validator.group.SelectGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 按 ID 获取菜单信息
     *
     * @param menuId 菜单 ID
     * @return {@link Result}<{@link MenuVO}>
     */
    @GetMapping("/{menuId}")
//    @SaCheckPermission("system:menu:detail")
    @Operation(summary = "根据菜单id获取菜单信息")
    public Result<MenuVO> getMenuById(@PathVariable Integer menuId){
        return Result.success(menuService.getMenuById(menuId));
    }

    /**
     * 查询菜单列表
     *
     * @param menuDTO 菜单 DTO
     * @return {@link Result}<{@link List}<{@link MenuVO}>>
     */
    @GetMapping("/list")
//    @SaCheckPermission("system:menu:list")
    @Operation(summary = "查询菜单列表", description = "查询菜单列表")
    public Result<List<MenuVO>> listMenuVO(@Validated(SelectGroup.class) MenuDTO menuDTO) {
        return Result.success(menuService.listMenuVO(menuDTO));
    }

    @GetMapping("/list/options")
//    @SaCheckPermission("system:menu:list")
    @Operation(summary = "查询菜单选项列表", description = "查询菜单选项列表")
    public Result<List<MenuOption>> listMenuOptions(){
        return Result.success(menuService.listMenuOptions());
    }

    /**
     * 添加菜单
     *
     * @param menuDTO 菜单 DTO
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping
//    @SaCheckPermission("system:menu:add")
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
//    @SaCheckPermission("system:menu:update")
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
//    @SaCheckPermission("system:menu:delete")
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
//    @SaCheckPermission("system:menu:tree")
    @Operation(summary = "获取菜单树", description = "获取菜单树")
    public Result<List<MenuTree>> listMenuTree() {
        return Result.success(menuService.listMenuTree());
    }
}
