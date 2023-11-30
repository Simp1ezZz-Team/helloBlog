package com.simple.helloblog.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple.helloblog.constant.CommonConstant;
import com.simple.helloblog.entity.User;
import com.simple.helloblog.enums.LoginTypeEnum;
import com.simple.helloblog.model.dto.LoginDTO;
import com.simple.helloblog.model.dto.RegisterDTO;
import com.simple.helloblog.service.LoginService;
import com.simple.helloblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;

    @Value("${sa-token.salt}")
    private String salt;

    /**
     * 用户登录
     *
     * @param loginDTO 登录参数
     * @return {@link String} token
     */
    @Override
    public String login(LoginDTO loginDTO) {
        User user = userService.getOne(Wrappers.<User>lambdaQuery()
                .select(List.of(User::getUserId))
                .eq(User::getUsername, loginDTO.getUsername())
                .eq(User::getPassword, SaSecureUtil.sha256BySalt(loginDTO.getPassword(), salt)));
        Assert.notNull(user, "用户名或密码错误");

        // 校验指定账号是否已被封禁，如果被封禁则抛出异常 `DisableServiceException`
        StpUtil.checkDisable(user.getUserId());
        // 通过校验后，再进行登录：
        StpUtil.login(user.getUserId());
        StpUtil.getSession().set(CommonConstant.USERNAME,loginDTO.getUsername());
        return StpUtil.getTokenValue();
    }

    /**
     * 用户邮箱注册
     *
     * @param registerDTO 注册 DTO
     */
    @Override
    public void register(RegisterDTO registerDTO) {
        User user = userService.getOne(Wrappers.<User>lambdaQuery()
                .select(List.of(User::getUserId))
                .eq(User::getUsername, registerDTO.getUsername()));
        Assert.isNull(user, "该邮箱已注册过账号，请直接登录！");
        // 用户信息入库
        User newUser = User.builder()
                .username(registerDTO.getUsername())
                .password(SaSecureUtil.sha256BySalt(registerDTO.getPassword(), salt))
                .nickname(CommonConstant.DEFAULT_USER_NICKNAME_PREFIX + IdWorker.getId())
                .email(registerDTO.getUsername())
                .disableFlag(CommonConstant.FALSE)
                .loginType(LoginTypeEnum.EMAIL.getType())
                .createBy(CommonConstant.ADMIN_USER_ID)
                .updateBy(CommonConstant.ADMIN_USER_ID).build();
        userService.save(newUser);
        //TODO 设置用户角色
    }
}
