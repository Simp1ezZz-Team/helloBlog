package com.simple.helloblog.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户注册DTO
 *
 * @author 魑魅魍魉
 * @date 2023/10/21 00:28:09
 */
@Data
@Schema(description = "用户注册DTO")
public class RegisterDTO {

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码不能少于6位")
    private String password;
}
