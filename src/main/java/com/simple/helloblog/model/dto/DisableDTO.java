package com.simple.helloblog.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 禁用 DTO
 *
 * @author 魑魅魍魉
 * @date 2023/11/17 23:04:34
 */
@Data
@Schema(description = "禁用状态")
public class DisableDTO {

    @Schema(description = "id")
    @NotNull(message = "id不能为空")
    private Integer id;

    @Schema(description = "是否禁用 (0否 1是)")
    @Max(value = 1, message = "是否禁用只能为0或1")
    @Min(value = 0, message = "是否禁用只能为0或1")
    private Integer disableFlag;

}
