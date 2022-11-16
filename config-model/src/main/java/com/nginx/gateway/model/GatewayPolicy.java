package com.nginx.gateway.model;

import com.nginx.gateway.model.rule.*;
import lombok.Builder;
import lombok.Data;

/**
 * <p>网关全局策略</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class GatewayPolicy {

    /**
     * 主进程及工作进程相关策略
     */
    private Processes processes;

    /**
     * 网关运维及指标监控策略
     */
    private Management management;

    /**
     * 日志输出策略
     */
    private Logging logging;

    /**
     * 客户端连接策略
     */
    private ClientConn client_conn;

    /**
     * 统一HTTP状态码定义
     */
    private StatusCode status_codes;

    /**
     * Basic 认证用户与密码
     */
    private Credentials credentials;

    /**
     * API Key 认证的Key和密钥
     */
    private ApiKeyAuth api_key_auth;
}
