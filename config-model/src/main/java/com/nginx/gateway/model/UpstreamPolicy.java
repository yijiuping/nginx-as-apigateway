package com.nginx.gateway.model;

import com.nginx.gateway.model.rule.HealthCheck;
import com.nginx.gateway.model.rule.LoadBalance;
import com.nginx.gateway.model.rule.SessionSticky;
import com.nginx.gateway.model.rule.UpstreamConn;
import lombok.Data;

/**
 * <p>上游服务集群策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class UpstreamPolicy {

    /**
     * 负载均衡策略
     */
    private LoadBalance load_balance;

    /**
     * 会话保持策略
     */
    private SessionSticky session_sticky;

    /**
     * 主动健康检查策略
     */
    private HealthCheck health_check;

    /**
     * 上游服务连接策略
     */
    private UpstreamConn upstream_conn;
}
