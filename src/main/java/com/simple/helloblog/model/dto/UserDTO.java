package com.simple.helloblog.model.dto;

import com.simple.helloblog.validator.group.InsertGroup;
import com.simple.helloblog.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户dto
 *
 * @author 魑魅魍魉
 * @date 2023/11/16 22:22:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "用户dto")
public class UserDTO extends AbstractPageDTO {

    @Schema(name = "用户id", type = "integer")
    @NotNull(message = "用户id不能为空", groups = {UpdateGroup.class})
    @Null(message = "用户id不能上送", groups = {InsertGroup.class})
    private Integer userId;

    @Schema(name = "用户名")
    @NotBlank(message = "用户名不能为空",groups = {InsertGroup.class, UpdateGroup.class})
    private String username;

    @Schema(name = "密码")
    @NotBlank(message = "密码不能为空",groups = {InsertGroup.class})
    @Size(min = 6, message = "密码不能少于6位",groups = {InsertGroup.class})
    private String password;

    @Schema(name = "用户昵称")
    @NotBlank(message = "昵称不能为空",groups = {InsertGroup.class, UpdateGroup.class})
    private String nickname;

    @Schema(name = "用户头像")
    private String avatar;

    @Schema(name = "登录方式 (0账号 1邮箱 2QQ 3Gitee 4Github)", type = "integer")
    private Integer loginType;

    @Schema(name = "用户角色Id列表")
    private List<Integer> roleIdList;

}
