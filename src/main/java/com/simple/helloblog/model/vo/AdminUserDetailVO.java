package com.simple.helloblog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "后台用户信息")
public class AdminUserDetailVO {
    /**
     * 用户标识
     */
    @Schema(description = "用户标识")
    private Integer userId;
    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;
    /**
     * 角色列表
     */
    @Schema(description = "角色列表")
    private List<String> roleList;
    /**
     * 权限列表
     */
    @Schema(description = "权限列表")
    private List<String> permissionList;
}
