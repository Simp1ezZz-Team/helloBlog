package com.simple.helloblog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.simple.helloblog.model.dto.LoginDTO;
import com.simple.helloblog.model.dto.RegisterDTO;
import com.simple.helloblog.model.vo.Result;
import com.simple.helloblog.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:17:01
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "登录模块", description = "登录模块相关接口")
public class LoginController {
    /**
     * 登录服务
     */
    private final LoginService loginService;

    /**
     * 登录
     *
     * @param loginDTO 登录 DTO
     * @return {@link Result}<{@link String}>
     */
    @Operation(summary = "登录", description = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated LoginDTO loginDTO) {
        return Result.success(loginService.login(loginDTO));
    }

    /**
     * 用户退出登录
     */
    @Operation(summary = "退出登录", description = "用户退出登录")
    @PostMapping("/logout")
    @SaCheckLogin
    public Result<Object> logout() {
        StpUtil.logout();
        return Result.success();
    }

    /**
     * 用户邮箱注册
     *
     * @param registerDTO 注册信息
     * @return {@link Result}
     */
    @Operation(summary = "用户邮箱注册", description = "用户邮箱注册")
    @PostMapping("/register")
    public Result<Object> register(@RequestBody @Validated RegisterDTO registerDTO) {
        loginService.register(registerDTO);
        return Result.success();
    }

    @GetMapping("/getCurrUser")
    @SaCheckLogin
    public Result<String> getCurrUser() {
        return Result.success(StpUtil.getLoginIdAsString());
    }
}
