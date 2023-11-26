package com.simple.helloblog.model.vo.tree;

import java.util.List;
import lombok.Data;

/**
 * 菜单选项
 *
 * @author simpleZzz
 * @date 2023/11/23 16:00:58
 */
@Data
public class MenuOption implements Tree<MenuOption> {
    /**
     * 菜单id
     */
    private Integer value;
    /**
     * 父菜单id
     */
    private Integer parentId;
    /**
     * 菜单名称
     */
    private String label;
    /**
     * 子菜单列表
     */
    private List<MenuOption> children;

    /**
     * 获取id
     *
     * @return id
     */
    @Override
    public Integer getTreeId() {
        return this.value;
    }

    /**
     * 获取父id
     *
     * @return 父id
     */
    @Override
    public Integer getParentTreeId() {
        return this.parentId;
    }

    /**
     * 设置子节点
     *
     * @param childrenTree 子节点
     */
    @Override
    public void setChildrenTree(List<MenuOption> childrenTree) {
        this.children = childrenTree;
    }
}
