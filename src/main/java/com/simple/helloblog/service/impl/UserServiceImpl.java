package com.simple.helloblog.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
import com.simple.helloblog.enums.LoginTypeEnum;
import com.simple.helloblog.enums.MenuTypeEnum;
import com.simple.helloblog.exception.ServiceException;
import com.simple.helloblog.mapper.UserMapper;
import com.simple.helloblog.model.dto.DisableDTO;
import com.simple.helloblog.model.dto.UserDTO;
import com.simple.helloblog.model.vo.AdminUserDetailVO;
import com.simple.helloblog.model.vo.AdminUserVO;
import com.simple.helloblog.model.vo.MetaVO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.model.vo.RouterVO;
import com.simple.helloblog.model.vo.UserMenuVO;
import com.simple.helloblog.model.vo.UserRoleVO;
import com.simple.helloblog.service.MenuService;
import com.simple.helloblog.service.RoleService;
import com.simple.helloblog.service.UserRoleService;
import com.simple.helloblog.service.UserService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final UserRoleService userRoleService;

    private final RoleService roleService;

    @Value("${sa-token.salt}")
    private String salt;

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
        // 先查用户列表
        Page<User> userPage = this.page(userDTO.toPage(), Wrappers.<User>lambdaQuery()
            .like(CharSequenceUtil.isNotBlank(userDTO.getNickname()), User::getNickname, userDTO.getNickname())
            .like(CharSequenceUtil.isNotBlank(userDTO.getUsername()), User::getUsername, userDTO.getUsername())
            .eq(userDTO.getLoginType() != null, User::getLoginType, userDTO.getLoginType())
            .orderByAsc(User::getUserId));
        IPage<AdminUserVO> adminUserVOIPage = userPage.convert(
            page -> BeanUtil.copyProperties(page, AdminUserVO.class));
        // 查询所有用户对应的角色列表
        List<UserRoleVO> userRoleVOList = roleService.selectJoinList(
            UserRoleVO.class, MPJWrappers.<Role>lambdaJoin()
                .select(Role::getRoleId, Role::getRoleName)
                .select(UserRole::getUserId)
                .leftJoin(UserRole.class, UserRole::getRoleId, Role::getRoleId)
                .in(UserRole::getUserId, adminUserVOIPage.getRecords().stream().map(AdminUserVO::getUserId).toList())
                .eq(Role::getDisableFlag, CommonConstant.FALSE));
        // 将角色列表设置到用户中
        adminUserVOIPage.getRecords().forEach(adminUserVO -> adminUserVO.setRoleList(userRoleVOList.stream()
            .filter(userRoleVO -> userRoleVO.getUserId().equals(adminUserVO.getUserId()))
            .sorted(Comparator.comparing(UserRoleVO::getRoleId)).toList()));
        return new PageResult<>(adminUserVOIPage);
    }

    /**
     * 修改用户禁用状态
     *
     * @param disableDTO {@link DisableDTO}
     */
    @Override
    public void updateStatus(DisableDTO disableDTO) {
        if (CommonConstant.ADMIN_USER_ID.equals(disableDTO.getId())) {
            throw new ServiceException("不允许禁用超级管理员");
        }
        this.updateById(User.builder().userId(disableDTO.getId()).disableFlag(disableDTO.getDisableFlag()).build());
        if (CommonConstant.TRUE.equals(disableDTO.getDisableFlag())) {
            // 先将账号踢下线
            StpUtil.logout(disableDTO.getId());
            // 再将账号封禁
            StpUtil.disable(disableDTO.getId(), -1);
        } else {
            // 解除封禁
            StpUtil.untieDisable(disableDTO.getId());
        }
    }

    /**
     * 更新用户
     *
     * @param userDTO {@link UserDTO}
     */
    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO, User.class);
        // 更新用户信息
        this.updateById(user);
        // 删除用户角色关联
        userRoleService.remove(Wrappers.lambdaUpdate(UserRole.class).eq(UserRole::getUserId, userDTO.getUserId()));
        // 重新添加用户角色关联
        List<UserRole> userRoleList = userDTO.getRoleIdList().stream().map(roleId -> UserRole.builder()
            .userId(userDTO.getUserId())
            .roleId(roleId).build()).toList();
        userRoleService.saveBatch(userRoleList);
        // 更新缓存中用户角色
        SaSession session = StpUtil.getSessionByLoginId(userDTO.getUserId());
        Optional.ofNullable(session).ifPresent(saSession -> saSession.delete(SaSession.ROLE_LIST));
    }

    /**
     * 添加用户
     *
     * @param userDTO 用户 dto
     */
    @Override
    @Transactional
    public void addUser(UserDTO userDTO) {
        // 用户名是否存在
        long countUsername = this.count(Wrappers.<User>lambdaQuery().eq(User::getUsername, userDTO.getUsername()));
        Assert.isFalse(countUsername > 0, "{}用户名已存在", userDTO.getUsername());
        // 添加用户
        User user = BeanUtil.copyProperties(userDTO, User.class);
        user.setPassword(SaSecureUtil.sha256BySalt(userDTO.getPassword(), salt));
        user.setLoginType(LoginTypeEnum.USERNAME.getType());
        if (CharSequenceUtil.isBlank(userDTO.getAvatar())) {
            user.setAvatar(CommonConstant.DEFAULT_USER_AVATAR);
        }
        this.save(user);
        // 添加用户角色关联
        List<UserRole> userRoleList = userDTO.getRoleIdList().stream().map(roleId -> UserRole.builder()
            .userId(user.getUserId())
            .roleId(roleId).build()).toList();
        userRoleService.saveBatch(userRoleList);
    }

    /**
     * 按 ID 删除用户
     *
     * @param userId 用户 ID
     */
    @Override
    @Transactional
    public void deleteUserById(Integer userId) {
        if (CommonConstant.ADMIN_USER_ID.equals(userId)) {
            throw new ServiceException("不允许删除超级管理员");
        }
        // 删除用户
        this.removeById(userId);
        // 删除用户角色关联
        userRoleService.remove(Wrappers.lambdaUpdate(UserRole.class).eq(UserRole::getUserId, userId));
        // 踢出用户
        StpUtil.logout(userId);
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
                    .component(CharSequenceUtil.blankToDefault(menu.getComponent(), CommonConstant.LAYOUT))
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
