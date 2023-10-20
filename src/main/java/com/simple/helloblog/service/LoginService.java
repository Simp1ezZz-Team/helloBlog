package com.simple.helloblog.service;

import com.simple.helloblog.model.dto.LoginDTO;
import com.simple.helloblog.model.dto.RegisterDTO;

/**
 * 登录服务
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:18:03
 */
public interface LoginService {
    /**
     * 用户登录
     *
     * @param loginDTO 登录参数
     * @return {@link String} token
     */
    String login(LoginDTO loginDTO);

    /**
     * 用户邮箱注册
     *
     * @param registerDTO 注册 DTO
     */
    void register(RegisterDTO registerDTO);
}
