package com.simple.helloblog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色 VO
 *
 * @author 魑魅魍魉
 * @date 2023/10/21 17:27:53
 */
@Data
@Schema(name = "角色 VO")
public class RoleVO {

    @Schema(name = "角色id", type = "integer")
    private Integer roleId;

    @Schema(name = "角色名称")
    private String roleName;

    @Schema(name = "角色描述")
    private String roleDesc;

    @Schema(name = "是否禁用 (0否 1是)", type = "integer")
    private Integer disableFlag;

    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(name = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //TODO 获取创建人和更新人的姓名
    @JsonIgnore
    private Integer createBy;
    @Schema(name = "创建人")
    private String createByName;

    @JsonIgnore
    private Integer updateBy;
    @Schema(name = "更新人")
    private String updateByName;
}
