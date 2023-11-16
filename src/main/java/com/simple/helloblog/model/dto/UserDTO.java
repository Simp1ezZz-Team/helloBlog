package com.simple.helloblog.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询参数dto
 *
 * @author 魑魅魍魉
 * @date 2023/11/16 22:22:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "用户查询参数dto")
public class UserDTO extends AbstractPageDTO {

    @Schema(name = "用户昵称")
    private String nickname;

    @Schema(name = "登录方式 (1邮箱 2QQ 3Gitee 4Github)", type = "integer")
    private Integer loginType;

}
