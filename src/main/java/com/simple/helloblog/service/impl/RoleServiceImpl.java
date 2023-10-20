package com.simple.helloblog.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.simple.helloblog.entity.Role;
import com.simple.helloblog.mapper.RoleMapper;
import com.simple.helloblog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色服务实现
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:10:48
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends MPJBaseServiceImpl<RoleMapper, Role> implements RoleService {
}
