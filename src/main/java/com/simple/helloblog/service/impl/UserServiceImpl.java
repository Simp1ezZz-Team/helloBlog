package com.simple.helloblog.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.simple.helloblog.entity.User;
import com.simple.helloblog.mapper.UserMapper;
import com.simple.helloblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:05:25
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserService {
}
