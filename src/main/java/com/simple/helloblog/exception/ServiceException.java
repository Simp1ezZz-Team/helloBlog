package com.simple.helloblog.exception;

import com.simple.helloblog.enums.StatusCodeEnum;
import lombok.Getter;

/**
 * 业务异常类
 *
 * @author 魑魅魍魉
 * @date 2023/10/21 00:09:48
 */
@Getter
public final class ServiceException extends RuntimeException {

    /**
     * 返回失败状态码
     */
    private final Integer code = StatusCodeEnum.FAIL.getCode();

    /**
     * 返回信息
     */
    private final String message;

    public ServiceException(String message) {
        this.message = message;
    }
}
