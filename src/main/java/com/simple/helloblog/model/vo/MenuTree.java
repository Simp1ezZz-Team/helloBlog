package com.simple.helloblog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 菜单树
 *
 * @author 魑魅魍魉
 * @date 2023/10/22 00:12:54
 */
@Data
public class MenuTree {

    @Schema(description = "菜单id")
    private Integer menuId;

    @Schema(description = "父菜单id")
    private Integer parentId;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "子菜单树")
    private List<MenuTree> children;
}
