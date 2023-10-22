package com.simple.helloblog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.toolkit.MPJWrappers;
import com.simple.helloblog.constant.CommonConstant;
import com.simple.helloblog.entity.Menu;
import com.simple.helloblog.entity.RoleMenu;
import com.simple.helloblog.mapper.MenuMapper;
import com.simple.helloblog.model.dto.MenuDTO;
import com.simple.helloblog.model.vo.MenuTree;
import com.simple.helloblog.model.vo.MenuVO;
import com.simple.helloblog.model.vo.PageResult;
import com.simple.helloblog.service.MenuService;
import com.simple.helloblog.service.RoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单服务实现
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:13:15
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends MPJBaseServiceImpl<MenuMapper, Menu> implements MenuService {

    /**
     * 角色菜单服务
     */
    private final RoleMenuService roleMenuService;


    /**
     * 查询菜单列表
     *
     * @param menuDTO 查询条件
     * @return {@link PageResult}<{@link MenuVO}>
     */
    @Override
    public PageResult<MenuVO> listMenuVO(MenuDTO menuDTO) {
        Page<Menu> menuPage = this.page(menuDTO.toPage(), Wrappers.<Menu>lambdaQuery()
                .eq(menuDTO.getMenuId() != null, Menu::getMenuId, menuDTO.getMenuId())
                .like(CharSequenceUtil.isNotBlank(menuDTO.getMenuName()), Menu::getMenuName, menuDTO.getMenuName())
                .eq(CharSequenceUtil.isNotBlank(menuDTO.getMenuType()), Menu::getMenuType, menuDTO.getMenuType())
                .eq(menuDTO.getDisableFlag() != null, Menu::getDisableFlag, menuDTO.getDisableFlag())
                .eq(menuDTO.getHiddenFlag() != null, Menu::getHiddenFlag, menuDTO.getHiddenFlag()));
        return new PageResult<>(menuPage.convert(menu -> BeanUtil.copyProperties(menu, MenuVO.class)));
    }

    /**
     * 添加菜单
     *
     * @param menuDTO 菜单 DTO
     */
    @Override
    public void addMenu(MenuDTO menuDTO) {
        // 菜单名是否已存在
        long countMenuName = this.count(Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getMenuName, menuDTO.getMenuName()));
        Assert.isFalse(countMenuName > 0, "{}菜单名已存在", menuDTO.getMenuName());

        Menu menu = BeanUtil.copyProperties(menuDTO, Menu.class);
        this.save(menu);
    }

    /**
     * 更新菜单
     *
     * @param menuDTO 菜单 DTO
     */
    @Override
    public void updateMenu(MenuDTO menuDTO) {
        // 菜单id是否存在
        long countId = this.count(Wrappers.<Menu>lambdaQuery().eq(Menu::getMenuId, menuDTO.getMenuId()));
        Assert.isTrue(countId > 0, "{}菜单不存在", menuDTO.getMenuId());
        // 菜单名是否已存在
        long countMenuName = this.count(Wrappers.<Menu>lambdaQuery().eq(Menu::getMenuName, menuDTO.getMenuName()));
        Assert.isFalse(countMenuName > 0, "{}菜单名已存在", menuDTO.getMenuName());

        Menu menu = BeanUtil.copyProperties(menuDTO, Menu.class);
        this.updateById(menu);
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单编号
     */
    @Override
    public void deleteMenu(Integer menuId) {
        //是否存在子菜单
        long countChild = this.count(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId, menuId));
        Assert.isFalse(countChild > 0, "存在子菜单,无法删除");
        //是否已分配
        long countRoleMenu = roleMenuService.count(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getMenuId, menuId));
        Assert.isFalse(countRoleMenu > 0, "菜单已分配,无法删除");
        this.removeById(menuId);
    }

    /**
     * 菜单树列表
     * 可能有多个一级菜单所以以列表形式返回
     *
     * @return {@link List}<{@link MenuTree}>
     */
    @Override
    public List<MenuTree> listMenuTree() {
        List<Menu> menuList = this.list(Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getHiddenFlag, CommonConstant.FALSE)
                .orderByAsc(Menu::getOrderNum));
        return recurMenuTreeList(CommonConstant.BASE_MENU_ID, BeanUtil.copyToList(menuList, MenuTree.class));
    }

    /**
     * 根据角色id查询菜单权限码List
     *
     * @param roleId 角色标识
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> listPermissionByRoleId(String roleId) {
        return this.selectJoinList(String.class, MPJWrappers.<Menu>lambdaJoin()
                .select(Menu::getPerms)
                .innerJoin(RoleMenu.class, RoleMenu::getMenuId, Menu::getMenuId)
                .eq(RoleMenu::getRoleId, roleId)
                .eq(Menu::getDisableFlag, CommonConstant.FALSE));
    }

    /**
     * 递归组合菜单树
     *
     * @param parentId 父菜单id
     * @param menuList 菜单列表
     * @return {@link List}<{@link MenuTree}>
     */
    private List<MenuTree> recurMenuTreeList(Integer parentId, List<MenuTree> menuList) {
        menuList.stream()
                .filter(menuTree -> menuTree.getParentId().equals(parentId))
                .forEach(menuTree -> menuTree.setChildren(recurMenuTreeList(menuTree.getMenuId(), menuList)));
        return menuList;
    }
}
