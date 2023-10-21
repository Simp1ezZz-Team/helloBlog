package com.simple.helloblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户角色关联表
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 21:56:06
 */
@Schema(description = "用户角色关联表")
@Data
@TableName(value = "t_user_role")
public class UserRole implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Integer id;
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @Schema(description = "用户id")
    private Integer userId;
    /**
     * 角色id
     */
    @TableField(value = "role_id")
    @Schema(description = "角色id")
    private String roleId;
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