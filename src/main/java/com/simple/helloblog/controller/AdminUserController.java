package com.simple.helloblog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.simple.helloblog.model.dto.UserDTO;
import com.simple.helloblog.model.vo.AdminUserDetailVO;
import com.simple.helloblog.model.vo.AdminUserVO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.Result;
import com.simple.helloblog.model.vo.RouterVO;
import com.simple.helloblog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 获取用户列表
     *
     * @param userDTO 用户 dto
     * @return {@link Result}<{@link List}<{@link AdminUserVO}>>
     */
    @GetMapping("/list")
    @Operation(summary = "获取后台用户列表")
//    @SaCheckPermission("user:list")
    public Result<PageResult<AdminUserVO>> list(UserDTO userDTO) {
        return Result.success(userService.listAdminUserVO(userDTO));
    }

    @GetMapping("/menu")
    @Operation(summary = "获取当前登录的用户菜单")
    @SaCheckLogin
    public Result<List<RouterVO>> getAdminUserMenu() {
        return Result.success(userService.getAdminUserMenu());
    }
}
