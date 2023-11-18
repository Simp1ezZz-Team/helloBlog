package com.simple.helloblog.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 抽象分页 DTO
 *
 * @author 魑魅魍魉
 * @date 2023/10/21 16:36:10
 */
@Data
@Schema(name = "抽象分页 DTO")
public abstract class AbstractPageDTO {

    /**
     * 当前页码
     */
    @Schema(name = "当前页码", type = "long")
    private Long pageNum;

    /**
     * 分页大小
     */
    @Schema(name = "分页大小", type = "long")
    private Long pageSize;

    public <T>Page<T> toPage() {
        return toPage(true);
    }
    public <T>Page<T> toPage(Boolean needCount) {
        return new Page<>(pageNum, pageSize,needCount);
    }
}
