package com.simple.helloblog.model.vo.tree;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

/**
 * 菜单树
 *
 * @author 魑魅魍魉
 * @date 2023/10/22 00:12:54
 */
@Data
public class MenuTree implements Tree<MenuTree> {

    @Schema(description = "菜单id")
    private Integer menuId;

    @Schema(description = "父菜单id")
    private Integer parentId;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "子菜单树")
    private List<MenuTree> children;

    /**
     * 获取id
     *
     * @return id
     */
    @Override
    public Integer getTreeId() {
        return this.menuId;
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
    public void setChildrenTree(List<MenuTree> childrenTree) {
        this.children = childrenTree;
    }

}
