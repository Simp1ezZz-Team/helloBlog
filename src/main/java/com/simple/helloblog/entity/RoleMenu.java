package com.simple.helloblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色菜单关联表
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 21:56:27
 */
@Schema(description = "角色菜单关联表")
@Data
@Builder
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
    /**
     * 删除标志
     */
    @TableField(value = "del_flag")
    @Schema(description = "删除标志")
    private Integer delFlag;
}