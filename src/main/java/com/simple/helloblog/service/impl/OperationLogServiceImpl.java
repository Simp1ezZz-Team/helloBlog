package com.simple.helloblog.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.simple.helloblog.entity.OperationLog;
import com.simple.helloblog.mapper.OperationLogMapper;
import com.simple.helloblog.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * 操作日志serviceImpl
 *
 * @author 魑魅魍魉
 * @date 2023/11/30 21:19:36
 */
@Service
public class OperationLogServiceImpl extends MPJBaseServiceImpl<OperationLogMapper, OperationLog> implements
    OperationLogService {

}
