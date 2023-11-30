package com.simple.helloblog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.simple.helloblog.annotation.OptLogger;
import com.simple.helloblog.model.dto.DisableDTO;
import com.simple.helloblog.model.dto.RoleDTO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.Result;
import com.simple.helloblog.model.vo.RoleVO;
import com.simple.helloblog.service.RoleService;
import com.simple.helloblog.validator.group.InsertGroup;
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

    /**
     * 查询角色列表
     *
     * @param roleDTO 查询参数
     * @return {@link Result}<{@link PageResult}<{@link RoleVO}>>
     */
    @GetMapping("/list")
    @SaCheckPermission("system:role:list")
    @Operation(summary = "查看角色列表", description = "查看角色列表")
    public Result<PageResult<RoleVO>> listRoleVO(@Validated({SelectGroup.class}) RoleDTO roleDTO) {
        return Result.success(roleService.listRoleVO(roleDTO));
    }

    /**
     * 按 ID 获取角色
     *
     * @param roleId 角色 ID
     * @return {@link Result}<{@link RoleVO}>
     */
    @GetMapping("/{roleId}")
    @SaCheckPermission("system:role:detail")
    @Operation(summary = "查看角色详情", description = "查看角色详情")
    public Result<RoleVO> getRoleById(@PathVariable Integer roleId) {
        return Result.success(roleService.getRoleById(roleId));
    }

    /**
     * 获取角色菜单 ID 列表
     *
     * @param roleId 角色 ID
     * @return {@link Result}<{@link List}<{@link Integer}>>
     */
    @GetMapping("/menu/{roleId}")
    @SaCheckPermission("system:role:detail")
    @Operation(summary = "获取角色菜单 ID 列表", description = "获取角色菜单 ID 列表")
    public Result<List<Integer>> getRoleMenuIdList(@PathVariable Integer roleId) {
        return Result.success(roleService.getRoleMenuIdList(roleId));
    }

    /**
     * 查询角色列表全部
     *
     * @param roleDTO 查询参数
     * @return {@link Result}<{@link PageResult}<{@link RoleVO}>>
     */
    @GetMapping("/list/all")
    @SaCheckPermission("system:role:list")
    @Operation(summary = "查看全部角色列表", description = "查看全部角色列表")
    public Result<List<RoleVO>> listAllRoleVO(@Validated({SelectGroup.class}) RoleDTO roleDTO) {
        return Result.success(roleService.listAllRoleVO(roleDTO));
    }

    /**
     * 添加角色
     *
     * @param roleDTO 角色 DTO
     * @return {@link Result}
     */
    @PostMapping
    @SaCheckPermission("system:role:add")
    @Operation(summary = "添加角色", description = "添加角色")
    @OptLogger("ADD")
    public Result<Object> addRole(@RequestBody @Validated({InsertGroup.class}) RoleDTO roleDTO) {
        roleService.addRole(roleDTO);
        return Result.success();
    }

    /**
     * 删除角色
     *
     * @param roleIdList 角色 ID 列表
     * @return {@link Result}<{@link ?}>
     */
    @DeleteMapping
    @SaCheckPermission("system:role:delete")
    @Operation(summary = "删除角色", description = "删除角色")
    public Result<Object> deleteRole(@RequestBody List<Integer> roleIdList) {
        roleService.batchDeleteRole(roleIdList);
        return Result.success();
    }

    /**
     * 更新角色
     *
     * @param roleDTO 角色 DTO
     * @return {@link Result}<{@link ?}>
     */
    @PatchMapping
    @SaCheckPermission("system:role:update")
    @Operation(summary = "修改角色", description = "修改角色")
    public Result<Object> updateRole(@RequestBody @Validated RoleDTO roleDTO) {
        roleService.updateRole(roleDTO);
        return Result.success();
    }

    /**
     * 更新角色状态
     *
     * @param disableDTO 禁用 DTO
     * @return {@link Result}<{@link ?}>
     */
    @PatchMapping("/status")
    @SaCheckPermission("system:role:update")
    @Operation(summary = "更新角色状态", description = "更新角色状态")
    public Result<Object> updateRoleStatus(@RequestBody @Validated DisableDTO disableDTO) {
        roleService.updateRoleStatus(disableDTO);
        return Result.success();
    }
}
