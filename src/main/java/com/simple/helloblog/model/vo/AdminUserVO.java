package com.simple.helloblog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * 后台用户VO
 *
 * @author 魑魅魍魉
 * @date 2023/11/16 22:18:49
 */
@Data
@Schema(name = "后台用户VO")
public class AdminUserVO {

    @Schema(name = "用户id", type = "integer")
    private Integer userId;

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "用户昵称")
    private String nickname;

    @Schema(name = "用户头像")
    private String avatar;

    @Schema(name = "登录ip")
    private String ipAddress;

    @Schema(name = "登录地址")
    private String ipSource;

    @Schema(name = "登录方式 (0账号 1邮箱 2QQ 3Gitee 4Github)", type = "integer")
    private Integer loginType;

    @Schema(name = "用户角色列表")
    private List<UserRoleVO> roleList;

    @Schema(name = "是否禁用 (0否 1是)", type = "integer")
    private Integer disableFlag;

    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(name = "最近登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

}
