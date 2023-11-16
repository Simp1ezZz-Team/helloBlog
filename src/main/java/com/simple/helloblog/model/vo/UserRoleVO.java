package com.simple.helloblog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户角色 VO
 *
 * @author 魑魅魍魉
 * @date 2023/11/16 22:16:49
 */
@Data
@Schema(name = "用户角色VO")
public class UserRoleVO {

    @Schema(name = "角色id", type = "integer")
    private Integer roleId;

    @Schema(name = "角色名称")
    private String roleName;

}
