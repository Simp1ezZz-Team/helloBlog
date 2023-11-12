package com.simple.helloblog.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * 路由 VO
 *
 * @author simpleZzz
 * @date 2023/11/12 13:35:42
 */
@Data
@Builder
@Schema(name = "路由VO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVO {

    /**
     * 路由name
     */
    @Schema(name = "路由name")
    private String name;

    /**
     * 路由路径
     */
    @Schema(name = "路由路径")
    private String path;

    /**
     * 路由组件
     */
    @Schema(name = "路由组件")
    private String component;

    /**
     * 路由元信息VO
     */
    @Schema(name = "路由元信息VO")
    private MetaVO metaVO;

    /**
     * 子路由列表
     */
    @Schema(name = "子路由列表")
    private List<RouterVO> children;

}
