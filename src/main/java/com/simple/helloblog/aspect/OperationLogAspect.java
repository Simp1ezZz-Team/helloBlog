package com.simple.helloblog.aspect;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.simple.helloblog.annotation.OptLogger;
import com.simple.helloblog.constant.CommonConstant;
import com.simple.helloblog.entity.OperationLog;
import com.simple.helloblog.service.OperationLogService;
import com.simple.helloblog.util.IpUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 操作日志切面
 *
 * @author 魑魅魍魉
 * @date 2023/11/28 20:40:45
 */
@Component
@Aspect
public class OperationLogAspect {

    /**
     * 请求开始时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private final OperationLogService operationLogService;

    public OperationLogAspect(@Autowired OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    /**
     * 设置操作日志切入点，在注解的位置切入代码
     */
    @Pointcut("@annotation(com.simple.helloblog.annotation.OptLogger)")
    public void optLogPointCut() {
    }

    /**
     * 前置通知
     */
    @Before("optLogPointCut()")
    public void doBefore() {
        // 记录请求开始时间
        startTime.set(System.currentTimeMillis());
    }

    /**
     * 连接点正常返回通知，拦截用户操作日志，正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切面信息
     * @param result 返回结果
     */
    @AfterReturning(pointcut = "optLogPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 获取切点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取注解
        Tag tag = (Tag) signature.getDeclaringType().getAnnotation(Tag.class);
        Operation operation = method.getAnnotation(Operation.class);
        OptLogger optLogger = method.getAnnotation(OptLogger.class);

        OperationLog operationLog = new OperationLog();
        operationLog.setModule(tag.name());
        operationLog.setType(optLogger.value());
        // 获取request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(requestAttributes).getRequest();
        operationLog.setUri(request.getRequestURI());
        // 获取类名及方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        operationLog.setName(className + "." + methodName);
        // 获取请求参数
        operationLog.setParams(JSONUtil.toJsonStr(joinPoint.getArgs()));
        operationLog.setMethod(request.getMethod());
        // 获取请求的客户端ip及地理位置信息
        String ipAddress = IpUtils.getIpAddress(request);
        operationLog.setIpAddress(ipAddress);
        String ipSource = IpUtils.getIpSource(ipAddress);
        operationLog.setIpSource(ipSource);
        // 获取用户id及用户名
        int userId = StpUtil.getLoginIdAsInt();
        SaSession saSession = StpUtil.getSessionByLoginId(userId);
        String username = saSession.getString(CommonConstant.USERNAME);
        operationLog.setUserId(userId);
        operationLog.setUsername(username);
        operationLog.setData(JSONUtil.toJsonStr(result));
        operationLog.setDescription(operation.summary());
        // 获取执行耗时
        operationLog.setTimes(System.currentTimeMillis() - startTime.get());
        startTime.remove();

        // 保存操作日志 TODO 改为异步保存
        operationLogService.save(operationLog);
    }
}
