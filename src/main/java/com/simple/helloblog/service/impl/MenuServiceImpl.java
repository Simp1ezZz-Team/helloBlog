package com.simple.helloblog.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.simple.helloblog.entity.Menu;
import com.simple.helloblog.mapper.MenuMapper;
import com.simple.helloblog.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 菜单服务实现
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:13:15
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends MPJBaseServiceImpl<MenuMapper, Menu> implements MenuService {
}
