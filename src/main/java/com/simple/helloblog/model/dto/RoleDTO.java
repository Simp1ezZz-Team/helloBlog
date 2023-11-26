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
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色 DTO
 *
 * @author 魑魅魍魉
 * @date 2023/10/21 16:06:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "角色DTO")
public class RoleDTO extends AbstractPageDTO {

    @Schema(description = "角色id")
    @NotNull(message = "角色id不能为空", groups = {UpdateGroup.class})
    @Null(message = "角色id不能上送", groups = {InsertGroup.class})
    private Integer roleId;

    @Schema(description = "角色名")
    @NotBlank(message = "角色名不能为空", groups = {InsertGroup.class})
    private String roleName;

    @Schema(description = "角色描述")
    private String roleDesc;

    @Schema(description = "是否禁用 (0否 1是)")
    @Max(value = 1, groups = {InsertGroup.class, UpdateGroup.class, SelectGroup.class}, message = "角色状态只能为0或1")
    @Min(value = 0, groups = {InsertGroup.class, UpdateGroup.class, SelectGroup.class}, message = "角色状态只能为0或1")
    private Integer disableFlag;

    @Schema(description = "菜单id集合")
    private List<Integer> menuIdList;
}
