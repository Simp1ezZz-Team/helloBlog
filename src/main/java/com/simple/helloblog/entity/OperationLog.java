package com.simple.helloblog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Schema(description="操作日志表")
@Data
@TableName(value = "t_operation_log")
public class OperationLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 操作id
     */
    @TableId(value = "operation_log_id", type = IdType.AUTO)
    @Schema(description="操作id")
    private Integer operationLogId;

    /**
     * 操作模块
     */
    @TableField(value = "`module`")
    @Schema(description="操作模块")
    private String module;

    /**
     * 操作类型
     */
    @TableField(value = "`type`")
    @Schema(description="操作类型")
    private String type;

    /**
     * 操作uri
     */
    @TableField(value = "uri")
    @Schema(description="操作uri")
    private String uri;

    /**
     * 方法名称
     */
    @TableField(value = "`name`")
    @Schema(description="方法名称")
    private String name;

    /**
     * 操作描述
     */
    @TableField(value = "description")
    @Schema(description="操作描述")
    private String description;

    /**
     * 请求参数
     */
    @TableField(value = "params")
    @Schema(description="请求参数")
    private String params;

    /**
     * 请求方式
     */
    @TableField(value = "`method`")
    @Schema(description="请求方式")
    private String method;

    /**
     * 返回数据
     */
    @TableField(value = "`data`")
    @Schema(description="返回数据")
    private String data;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @Schema(description="用户id")
    private Integer userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @Schema(description="用户名")
    private String username;

    /**
     * 操作ip
     */
    @TableField(value = "ip_address")
    @Schema(description="操作ip")
    private String ipAddress;

    /**
     * 操作地址
     */
    @TableField(value = "ip_source")
    @Schema(description="操作地址")
    private String ipSource;

    /**
     * 操作耗时 (毫秒)
     */
    @TableField(value = "times")
    @Schema(description="操作耗时 (毫秒)")
    private Long times;

    /**
     * 操作时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @Schema(description="操作时间")
    private LocalDateTime createTime;
}