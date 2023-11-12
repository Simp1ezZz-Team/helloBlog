package com.simple.helloblog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 路由元信息VO
 *
 * @author simpleZzz
 * @date 2023/11/12 13:29:11
 */
@Data
@Builder
@Schema(name = "路由元信息VO")
public class MetaVO {

    /**
     * 菜单名称
     */
    @Schema(name = "菜单名称")
    private String title;

    /**
     * 菜单图标
     */
    @Schema(name = "菜单图标")
    private String icon;

    /**
     * 是否隐藏
     */
    @Schema(name = "是否隐藏")
    private Boolean hidden;
}
