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
 * 用户表
 *
 * @author 魑魅魍魉
 * @date 2023/10/20 21:56:19
 */
@Schema(description = "用户表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @Schema(description = "用户id")
    private Integer userId;
    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    @Schema(description = "用户昵称")
    private String nickname;
    /**
     * 用户名
     */
    @TableField(value = "username")
    @Schema(description = "用户名")
    private String username;
    /**
     * 用户密码
     */
    @TableField(value = "`password`")
    @Schema(description = "用户密码")
    private String password;
    /**
     * 头像
     */
    @TableField(value = "avatar")
    @Schema(description = "头像")
    private String avatar;
    /**
     * 个人网站
     */
    @TableField(value = "web_site")
    @Schema(description = "个人网站")
    private String webSite;
    /**
     * 个人简介
     */
    @TableField(value = "intro")
    @Schema(description = "个人简介")
    private String intro;
    /**
     * 邮箱
     */
    @TableField(value = "email")
    @Schema(description = "邮箱")
    private String email;
    /**
     * 登录ip
     */
    @TableField(value = "ip_address")
    @Schema(description = "登录ip")
    private String ipAddress;
    /**
     * 登录地址
     */
    @TableField(value = "ip_source")
    @Schema(description = "登录地址")
    private String ipSource;
    /**
     * 登录方式 (1邮箱 2QQ 3Gitee 4Github)
     */
    @TableField(value = "login_type")
    @Schema(description = "登录方式 (0账号 1邮箱 2QQ 3Gitee 4Github)")
    private Integer loginType;
    /**
     * 是否禁用 (0否 1是)
     */
    @TableField(value = "disable_flag")
    @Schema(description = "是否禁用 (0否 1是)")
    private Integer disableFlag;
    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    @Schema(description = "登录时间")
    private LocalDateTime loginTime;
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