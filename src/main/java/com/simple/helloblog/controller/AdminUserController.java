package com.simple.helloblog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.simple.helloblog.model.dto.DisableDTO;
import com.simple.helloblog.model.dto.UserDTO;
import com.simple.helloblog.model.vo.AdminUserDetailVO;
import com.simple.helloblog.model.vo.AdminUserVO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.Result;
import com.simple.helloblog.model.vo.RouterVO;
import com.simple.helloblog.service.UserService;
import com.simple.helloblog.validator.group.UpdateGroup;
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
 * 用户控制器
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 21:58:20
 */
@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
@Tag(name = "后台用户模块", description = "后台用户模块相关接口")
public class AdminUserController {

    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 获取当前登录的用户信息
     *
     * @return {@link Result}<{@link AdminUserDetailVO}>
     */
    @GetMapping("/info")
    @Operation(summary = "获取当前登录的用户信息")
    @SaCheckLogin
    public Result<AdminUserDetailVO> getAdminUserInfo() {
        return Result.success(userService.getAdminUserInfo());
    }

    /**
     * 按 ID 获取用户信息
     *
     * @param userId 用户 ID
     * @return {@link Result}<{@link AdminUserVO}>
     */
    @GetMapping("/{userId}")
    @Operation(summary = "根据用户id获取用户信息")
//    @SaCheckPermission("system:user:detail")
    public Result<AdminUserVO> getAdminUserById(@PathVariable Integer userId){
        return Result.success(userService.getAdminUserById(userId));
    }

    /**
     * 获取用户列表
     *
     * @param userDTO 用户 dto
     * @return {@link Result}<{@link List}<{@link AdminUserVO}>>
     */
    @GetMapping("/list")
    @Operation(summary = "获取后台用户列表")
    @SaCheckPermission("system:user:list")
    public Result<PageResult<AdminUserVO>> list(@Validated UserDTO userDTO) {
        return Result.success(userService.listAdminUserVO(userDTO));
    }

    /**
     * 获取当前登录的用户菜单
     *
     * @return 路由菜单列表 {@link Result}<{@link List}<{@link RouterVO}>>
     */
    @GetMapping("/menu")
    @Operation(summary = "获取当前登录的用户菜单")
    @SaCheckLogin
    public Result<List<RouterVO>> getAdminUserMenu() {
        return Result.success(userService.getAdminUserMenu());
    }

    /**
     * 修改用户状态
     *
     * @param disableDTO 禁用dto
     * @return {@link Result}
     */
    @PatchMapping("status")
    @Operation(summary = "修改用户状态")
//    @SaCheckPermission("system:user:update")
    public Result<Object> updateStatus(@Validated @RequestBody DisableDTO disableDTO) {
        userService.updateUserStatus(disableDTO);
        return Result.success();
    }

    /**
     * 修改用户信息
     *
     * @param userDTO 用户dto
     * @return {@link Result}
     */
    @PatchMapping
    @Operation(summary = "修改用户信息")
//    @SaCheckPermission("system:user:update")
    public Result<Object> updateUser(@Validated(UpdateGroup.class) @RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return Result.success();
    }

    /**
     * 添加用户
     *
     * @param userDTO 用户dto
     * @return {@link Result}
     */
    @PostMapping
    @Operation(summary = "添加用户")
//    @SaCheckPermission("system:user:add")
    public Result<Object> addUser(@Validated @RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return Result.success();
    }

    /**
     * 删除用户
     *
     * @param userIds 用户 ID
     * @return {@link Result}<{@link Object}>
     */
    @DeleteMapping
    @Operation(summary = "删除用户")
//    @SaCheckPermission("system:user:delete")
    public Result<Object> deleteUser(@RequestBody List<Integer> userIds){
        userService.batchDeleteUser(userIds);
        return Result.success();
    }
}
