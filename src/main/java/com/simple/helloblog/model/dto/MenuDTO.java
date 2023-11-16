package com.simple.helloblog.model.dto;

import com.simple.helloblog.validator.group.InsertGroup;
import com.simple.helloblog.validator.group.SelectGroup;
import com.simple.helloblog.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "菜单DTO")
public class MenuDTO extends AbstractPageDTO {

    @Schema(description = "菜单id")
    @NotNull(message = "菜单id不能为空", groups = {UpdateGroup.class})
    @Null(message = "菜单id不能上送", groups = {InsertGroup.class})
    private Integer menuId;

    @Schema(description = "父级菜单id")
    private Integer parentId;

    @Schema(description = "菜单名称")
    @NotBlank(message = "菜单名称不能为空", groups = {InsertGroup.class})
    private String menuName;

    @Schema(description = "菜单类型（M目录 C菜单 B按钮）")
    @NotBlank(message = "菜单类型不能为空", groups = {InsertGroup.class})
    private String menuType;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "菜单组件")
    private String component;

    @Schema(description = "权限标识")
    private String perms;

    @Schema(description = "菜单排序")
    @NotNull(message = "菜单排序不能为空", groups = {InsertGroup.class})
    @Min(value = 0, message = "菜单排序不能小于0", groups = {InsertGroup.class})
    private Integer orderNum;

    @Schema(description = "是否隐藏（0否 1是）")
    @Max(value = 1, message = "是否隐藏只能为0或1", groups = {InsertGroup.class, UpdateGroup.class, SelectGroup.class})
    @Min(value = 0, message = "是否隐藏只能为0或1", groups = {InsertGroup.class, UpdateGroup.class, SelectGroup.class})
    private Integer hiddenFlag;

    @Schema(description = "是否禁用（0否 1是）")
    @Max(value = 1, message = "是否禁用只能为0或1", groups = {InsertGroup.class, UpdateGroup.class, SelectGroup.class})
    @Min(value = 0, message = "是否禁用只能为0或1", groups = {InsertGroup.class, UpdateGroup.class, SelectGroup.class})
    private Integer disableFlag;
}
