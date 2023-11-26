package com.simple.helloblog.model.vo.tree;

import java.util.List;

public interface Tree<T> {

    /**
     * 获取id
     *
     * @return id
     */
    Integer getTreeId();

    /**
     * 获取父id
     *
     * @return 父id
     */
    Integer getParentTreeId();

    /**
     * 设置子节点
     *
     * @param childrenTree 子节点
     */
    void setChildrenTree(List<T> childrenTree);
}
