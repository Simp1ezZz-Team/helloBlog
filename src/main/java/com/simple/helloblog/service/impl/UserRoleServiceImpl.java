package com.simple.helloblog.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.simple.helloblog.entity.UserRole;
import com.simple.helloblog.mapper.UserRoleMapper;
import com.simple.helloblog.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色服务 IMPL
 *
 * @author 魑魅魍魉
 * @date 2023/10/21 22:46:22
 */
@Service
public class UserRoleServiceImpl extends MPJBaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
