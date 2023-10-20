package com.simple.helloblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单表
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 21:56:45
 */
@Schema
@Data
@TableName(value = "t_menu")
public class Menu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Integer id;
    /**
     * 父菜单id (paren_id为0且type为M则是一级菜单)
     */
    @TableField(value = "parent_id")
    @Schema(description = "父菜单id (paren_id为0且type为M则是一级菜单)")
    private Integer parentId;
    /**
     * 权限类型 (M目录 C菜单 B按钮)
     */
    @TableField(value = "menu_type")
    @Schema(description = "权限类型 (M目录 C菜单 B按钮)")
    private String menuType;
    /**
     * 名称
     */
    @TableField(value = "menu_name")
    @Schema(description = "名称")
    private String menuName;
    /**
     * 路由地址
     */
    @TableField(value = "`path`")
    @Schema(description = "路由地址")
    private String path;
    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    @Schema(description = "菜单图标")
    private String icon;
    /**
     * 菜单组件
     */
    @TableField(value = "component")
    @Schema(description = "菜单组件")
    private String component;
    /**
     * 权限标识
     */
    @TableField(value = "perms")
    @Schema(description = "权限标识")
    private String perms;
    /**
     * 是否隐藏 (0否 1是)
     */
    @TableField(value = "is_hidden")
    @Schema(description = "是否隐藏 (0否 1是)")
    private Integer isHidden;
    /**
     * 是否禁用 (0否 1是)
     */
    @TableField(value = "is_disable")
    @Schema(description = "是否禁用 (0否 1是)")
    private Integer isDisable;
    /**
     * 排序
     */
    @TableField(value = "order_num")
    @Schema(description = "排序")
    private Integer orderNum;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    /**
     * 创建者
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @Schema(description = "创建者")
    private Integer createBy;
    /**
     * 更新者
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新者")
    private Integer updateBy;
}