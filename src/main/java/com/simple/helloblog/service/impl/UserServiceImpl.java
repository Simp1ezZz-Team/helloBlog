package com.simple.helloblog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.toolkit.MPJWrappers;
import com.simple.helloblog.constant.CommonConstant;
import com.simple.helloblog.entity.Menu;
import com.simple.helloblog.entity.Role;
import com.simple.helloblog.entity.RoleMenu;
import com.simple.helloblog.entity.User;
import com.simple.helloblog.entity.UserRole;
import com.simple.helloblog.enums.MenuTypeEnum;
import com.simple.helloblog.mapper.UserMapper;
import com.simple.helloblog.model.dto.UserDTO;
import com.simple.helloblog.model.vo.AdminUserDetailVO;
import com.simple.helloblog.model.vo.AdminUserVO;
import com.simple.helloblog.model.vo.MetaVO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.RouterVO;
import com.simple.helloblog.model.vo.UserMenuVO;
import com.simple.helloblog.service.MenuService;
import com.simple.helloblog.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private final MenuService menuService;

    /**
     * 获取当前登录的后台用户信息
     *
     * @return {@link AdminUserDetailVO}
     */
    @Override
    public AdminUserDetailVO getAdminUserInfo() {
        Integer userId = StpUtil.getLoginIdAsInt();
        User user = this.getOne(Wrappers.<User>lambdaQuery()
            .select(List.of(User::getNickname, User::getAvatar))
            .eq(User::getUserId, userId));
        List<String> roleList = StpUtil.getRoleList(userId);
        List<String> permissionList = StpUtil.getPermissionList();
        return AdminUserDetailVO.builder()
            .userId(userId)
            .avatar(user.getAvatar())
            .nickname(user.getNickname())
            .roleList(roleList)
            .permissionList(permissionList).build();
    }

    /**
     * 获取当前登录的后台用户的菜单信息
     *
     * @return {@link List}<{@link RouterVO}>
     */
    @Override
    public List<RouterVO> getAdminUserMenu() {
        int userId = StpUtil.getLoginIdAsInt();
        List<UserMenuVO> userMenuVOList = menuService.selectJoinList(UserMenuVO.class, MPJWrappers.<Menu>lambdaJoin()
            .select(Menu::getMenuId, Menu::getParentId, Menu::getMenuName, Menu::getMenuType, Menu::getPath,
                Menu::getIcon, Menu::getComponent, Menu::getHiddenFlag)
            .innerJoin(RoleMenu.class, RoleMenu::getMenuId, Menu::getMenuId)
            .innerJoin(UserRole.class, UserRole::getRoleId, RoleMenu::getRoleId)
            .innerJoin(User.class, User::getUserId, UserRole::getUserId)
            .innerJoin(Role.class, Role::getRoleId, UserRole::getRoleId)
            .eq(User::getUserId, userId)
            .eq(Role::getDisableFlag, CommonConstant.FALSE)
            .eq(User::getDisableFlag, CommonConstant.FALSE)
            .eq(Menu::getDisableFlag, CommonConstant.FALSE)
            .in(Menu::getMenuType, List.of(MenuTypeEnum.DIRECTORY.getType(), MenuTypeEnum.MENU.getType())));
        return recurRouter(CommonConstant.BASE_MENU_ID, userMenuVOList);
    }

    /**
     * 用户列表
     *
     * @param userDTO 用户 dto
     * @return {@link List}<{@link AdminUserVO}>
     */
    @Override
    public PageResult<AdminUserVO> listAdminUserVO(UserDTO userDTO) {
        Page<AdminUserVO> voPage = this.selectJoinListPage(userDTO.toPage(), AdminUserVO.class,
            MPJWrappers.<User>lambdaJoin()
                .select(User::getUserId, User::getNickname, User::getAvatar, User::getDisableFlag, User::getIpAddress,
                    User::getIpSource, User::getLoginType, User::getLoginTime, User::getCreateTime)
                .selectCollection(Role.class, AdminUserVO::getRoleList, map -> map
                    .id(Role::getRoleId)
                    .result(Role::getRoleName))
                .leftJoin(UserRole.class, UserRole::getUserId, User::getUserId)
                .leftJoin(Role.class, Role::getRoleId, UserRole::getRoleId)
                .like(CharSequenceUtil.isNotBlank(userDTO.getNickname()), User::getNickname, userDTO.getNickname())
                .eq(userDTO.getLoginType() != null, User::getLoginType, userDTO.getLoginType()));
        return new PageResult<>(voPage);
    }

    /**
     * 递归获取路由
     *
     * @param parentId 父菜单id
     * @param menuList 菜单集合
     * @return {@link RouterVO}
     */
    private List<RouterVO> recurRouter(Integer parentId, List<UserMenuVO> menuList) {
        List<RouterVO> routerVOList = new ArrayList<>();
        Optional.ofNullable(menuList).ifPresent(menus -> menus.stream()
            .filter(menu -> menu.getParentId().equals(parentId))
            .forEach(menu -> {
                RouterVO routerVO = RouterVO.builder()
                    .name(menu.getMenuName())
                    .path(menu.getPath())
                    .component(CharSequenceUtil.blankToDefault(menu.getComponent(),CommonConstant.LAYOUT))
                    .meta(MetaVO.builder()
                        .title(menu.getMenuName())
                        .icon(menu.getIcon())
                        .hidden(CommonConstant.TRUE.equals(menu.getHiddenFlag())).build()).build();
                if (menu.getMenuType().equals(MenuTypeEnum.DIRECTORY.getType())) {
                    routerVO.setChildren(recurRouter(menu.getMenuId(), menuList));
                }
                routerVOList.add(routerVO);
            }));
        return routerVOList;
    }
}
