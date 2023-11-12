package com.simple.helloblog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户菜单VO
 *
 * @author 魑魅魍魉
 * @date 2023/11/12 22:06:04
 */
@Data
@Schema(name = "用户菜单VO")
public class UserMenuVO {

    /**
     * 菜单id
     */
    @Schema(name = "菜单id", type = "integer")
    private Integer menuId;

    /**
     * 父菜单id
     */
    @Schema(name = "父菜单id", type = "integer")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @Schema(name = "菜单名称")
    private String menuName;

    /**
     * 权限类型 (M目录 C菜单 B按钮)
     */
    @Schema(name = "权限类型 (M目录 C菜单 B按钮)")
    private String menuType;

    /**
     * 菜单路由
     */
    @Schema(name = "菜单路由")
    private String path;

    /**
     * 菜单图标
     */
    @Schema(name = "菜单图标")
    private String icon;

    /**
     * 菜单组件
     */
    @Schema(name = "菜单组件")
    private String component;

    /**
     * 是否隐藏 (0否 1是)
     */
    @Schema(name = "是否隐藏 (0否 1是)", type = "integer")
    private Integer hiddenFlag;
}
