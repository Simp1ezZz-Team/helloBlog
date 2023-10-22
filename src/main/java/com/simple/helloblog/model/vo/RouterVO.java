package com.simple.helloblog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "路由VO")
public class RouterVO {
    private String name;

    private String path;

    private String component;

}
