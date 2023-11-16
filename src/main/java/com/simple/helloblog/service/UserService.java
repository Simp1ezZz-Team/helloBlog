package com.simple.helloblog.service;

import com.github.yulichang.base.MPJBaseService;
import com.simple.helloblog.entity.User;
import com.simple.helloblog.model.dto.UserDTO;
import com.simple.helloblog.model.vo.AdminUserDetailVO;
import com.simple.helloblog.model.vo.AdminUserVO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.RouterVO;
import java.util.List;

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
     * @return {@link AdminUserDetailVO}
     */
    AdminUserDetailVO getAdminUserInfo();

    /**
     * 获取当前登录的后台用户的菜单信息
     *
     * @return {@link List}<{@link RouterVO}>
     */
    List<RouterVO> getAdminUserMenu();

    /**
     * 用户列表
     *
     * @param userDTO 用户 dto
     * @return {@link List}<{@link AdminUserVO}>
     */
    PageResult<AdminUserVO> listAdminUserVO(UserDTO userDTO);
}
