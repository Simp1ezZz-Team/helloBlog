package com.simple.helloblog.service;

import com.github.yulichang.base.MPJBaseService;
import com.simple.helloblog.entity.User;
import com.simple.helloblog.model.vo.AdminUserVO;
import com.simple.helloblog.model.vo.RouterVO;

/**
 * 用户服务
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:00:22
 */
public interface UserService extends MPJBaseService<User> {
    /**
     * 获取当前登录的后台用户信息
     *
     * @return {@link AdminUserVO}
     */
    AdminUserVO getAdminUserInfo();

    /**
     * 获取当前登录的后台用户的菜单信息
     *
     * @return {@link RouterVO}
     */
    RouterVO getAdminUserMenu();
}
