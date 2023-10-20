package com.simple.helloblog.controller;

import com.simple.helloblog.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "用户模块", description = "用户模块相关接口")
public class UserController {
    /**
     * 用户服务
     */
    private final UserService userService;
}
