package com.simple.helloblog.model.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页返回结果类
 *
 * @author 魑魅魍魉
 * @date 2023/10/21 16:27:13
 */
@Data
@Schema(name = "分页返回结果类")
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    /**
     * 分页数据
     */
    @Schema(name = "分页数据")
    private List<T> records;

    /**
     * 数据总条数
     */
    @Schema(name = "数据总条数", type = "long")
    private Long total;

    /**
     * 每页大小
     */
    @Schema(name = "每页大小", type = "long")
    private Long size;

    /**
     * 当前页数
     */
    @Schema(name = "当前页数", type = "long")
    private Long current;

    public PageResult(IPage<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getCurrent();
    }
}
