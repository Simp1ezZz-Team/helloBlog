package com.simple.helloblog.handler;

import cn.dev33.satoken.exception.NotLoginException;
import com.simple.helloblog.enums.StatusCodeEnum;
import com.simple.helloblog.exception.ServiceException;
import com.simple.helloblog.model.vo.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;
import java.util.Objects;

/**
 * 全局异常处理
 *
 * @author 魑魅魍魉
 * @date 2023/10/21 00:11:43
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     *
     * @param e e
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(value = ServerException.class)
    public Result<Object> handlerServiceException(ServiceException e) {
        return Result.fail(e.getMessage());
    }

    /**
     * 处理Assert异常
     * @param e e
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<Object> handlerIllegalArgumentException(IllegalArgumentException e) {
        return Result.fail(e.getMessage());
    }

    /**
     * 处理参数校验异常
     *
     * @param e e
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Result.fail(StatusCodeEnum.VALID_ERROR.getCode(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 处理程序未登录异常
     *
     * @param e e
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(value = NotLoginException.class)
    public Result<Object> handlerNotLoginException(NotLoginException e) {
        return Result.fail(StatusCodeEnum.UNAUTHORIZED.getCode(), e.getMessage());
    }
}
