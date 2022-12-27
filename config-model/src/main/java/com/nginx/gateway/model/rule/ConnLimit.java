package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>并发连接限制策略</p>
 * <p>
 * limit_conn_zone $binary_remote_addr zone=perip:10m;
 * limit_conn_zone $server_name zone=perserver:10m;
 * <p>
 * server {
 * ...
 * limit_conn perip 10;
 * limit_conn perserver 100;
 * }
 *
 * @author Jiuping Yi
 */
@Data
public class ConnLimit {

    /**
     * 计数器，可以是变量、文本，或他们的组合
     */
    private String key = "$server_name";

    /**
     * 计数器内存空间名称
     */
    private String zone_name = "perserver";

    /**
     * 计数器内存空间大小
     */
    private String zone_size = "10m";

    /**
     * 并发连接数
     */
    private Integer conn = 65535;

    /**
     * 并发连接限制日志级别
     */
    private String log_level;

}
