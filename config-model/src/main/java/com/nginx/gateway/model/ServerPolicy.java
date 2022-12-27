package com.nginx.gateway.model;

import com.nginx.gateway.model.rule.*;
import lombok.Data;

/**
 * <p>网关下虚拟服务的通用策略</p>
 *
 * @author Jiuping Yi
 */

@Data
public class ServerPolicy {

    /**
     * IP访问控制策略
     */
    private IpAccessControl ip_access_control;

    /**
     * 日志输出策略
     */
    private Logging logging;

    /**
     * 客户端连接策略
     */
    private ClientConn client_conn;

    /**
     * 并发连接限制策略
     */
    private ConnLimit conn_limit;

    /**
     * TLS/SSL卸载策略
     */
    private SslOffload ssl_offload;

    /**
     * TLS/SSL重定向策略
     */
    private Redirect redirect;

    /**
     * 客户端请求缓冲策略
     */
    private ClientBuffering client_buffer;

    /**
     * 上游服务响应缓冲策略
     */
    private ProxyBuffering proxy_buffer;

}
