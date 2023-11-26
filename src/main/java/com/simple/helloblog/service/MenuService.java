package com.simple.helloblog.service;

import com.github.yulichang.base.MPJBaseService;
import com.simple.helloblog.entity.Menu;
import com.simple.helloblog.model.dto.MenuDTO;
import com.simple.helloblog.model.vo.tree.MenuOption;
import com.simple.helloblog.model.vo.tree.MenuTree;
import com.simple.helloblog.model.vo.tree.MenuVO;
import java.util.List;

/**
 * 菜单服务
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 22:12:24
 */
public interface MenuService extends MPJBaseService<Menu> {

    /**
     * 查询菜单列表
     *
     * @param menuDTO 查询条件
     * @return {@link List}<{@link MenuVO}>
     */
    List<MenuVO> listMenuVO(MenuDTO menuDTO);

    /**
     * 添加菜单
     *
     * @param menuDTO 菜单 DTO
     */
    void addMenu(MenuDTO menuDTO);

    /**
     * 更新菜单
     *
     * @param menuDTO 菜单 DTO
     */
    void updateMenu(MenuDTO menuDTO);

    /**
     * 删除菜单
     *
     * @param menuId 菜单编号
     */
    void deleteMenu(Integer menuId);

    /**
     * 菜单树列表 可能有多个一级菜单所以以列表形式返回
     *
     * @return {@link List}<{@link MenuTree}>
     */
    List<MenuTree> listMenuTree();

    /**
     * 根据角色id查询菜单权限码List
     *
     * @param roleId 角色标识
     * @return {@link List}<{@link String}>
     */
    List<String> listPermissionByRoleId(String roleId);

    /**
     * 列表菜单选项
     *
     * @return {@link List}<{@link MenuOption}>
     */
    List<MenuOption> listMenuOptions();

    /**
     * 按 ID 获取菜单
     *
     * @param menuId 菜单 ID
     * @return {@link MenuVO}
     */
    MenuVO getMenuById(Integer menuId);
}
