package com.simple.helloblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 21:56:35
 */
@Schema(description = "角色表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_role")
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    @Schema(description = "主键id")
    private Integer roleId;
    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    @Schema(description = "角色名称")
    private String roleName;
    /**
     * 角色描述
     */
    @TableField(value = "role_desc")
    @Schema(description = "角色描述")
    private String roleDesc;
    /**
     * 是否禁用 (0否 1是)
     */
    @TableField(value = "disable_flag")
    @Schema(description = "是否禁用 (0否 1是)")
    private Integer disableFlag;
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