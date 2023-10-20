package com.simple.helloblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色菜单关联表
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 21:56:27
 */
@Schema
@Data
@TableName(value = "t_role_menu")
public class RoleMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Integer id;
    /**
     * 角色id
     */
    @TableField(value = "role_id")
    @Schema(description = "角色id")
    private Integer roleId;
    /**
     * 菜单id
     */
    @TableField(value = "menu_id")
    @Schema(description = "菜单id")
    private Integer menuId;
}