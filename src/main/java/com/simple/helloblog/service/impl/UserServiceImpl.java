package com.simple.helloblog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.simple.helloblog.entity.User;
import com.simple.helloblog.mapper.UserMapper;
import com.simple.helloblog.model.vo.AdminUserVO;
import com.simple.helloblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:05:25
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserService {
    /**
     * 获取当前登录的后台用户信息
     *
     * @return {@link AdminUserVO}
     */
    @Override
    public AdminUserVO getAdminUserInfo() {
        Integer userId = StpUtil.getLoginIdAsInt();
        User user = this.getOne(Wrappers.<User>lambdaQuery()
                .select(List.of(User::getNickname, User::getAvatar))
                .eq(User::getUserId, userId));
        List<String> roleList = StpUtil.getRoleList(userId);
        List<String> permissionList = StpUtil.getPermissionList();
        return AdminUserVO.builder()
                .userId(userId)
                .avatar(user.getAvatar())
                .nickname(user.getNickname())
                .roleList(roleList)
                .permissionList(permissionList).build();
    }
}
