package com.simple.helloblog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "菜单 VO")
public class MenuVO {

    @Schema(name = "菜单id", type = "integer")
    private Integer menuId;

    @Schema(name = "父级菜单id", type = "integer")
    private Integer parentId;

    @Schema(name = "菜单类型（M目录 C菜单 B按钮）")
    private String menuType;

    @Schema(name = "菜单名称")
    private String menuName;

    @Schema(name = "路由地址")
    private String path;

    @Schema(name = "菜单图标")
    private String icon;

    @Schema(name = "菜单组件")
    private String component;

    @Schema(name = "权限标识")
    private String perms;

    @Schema(name="是否隐藏（0否 1是）", type = "integer")
    private Integer hiddenFlag;

    @Schema(name="是否禁用（0否 1是）", type = "integer")
    private Integer disableFlag;

    @Schema(name = "菜单排序", type = "integer")
    private Integer orderNum;

    @JsonIgnore
    private Integer createBy;
    @Schema(name = "创建人")
    private String createByName;

    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonIgnore
    private Integer updateBy;
    @Schema(name = "更新人")
    private String updateByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(name = "更新时间")
    private LocalDateTime updateTime;

    @Schema(name = "子菜单")
    private List<MenuVO> children;
}
