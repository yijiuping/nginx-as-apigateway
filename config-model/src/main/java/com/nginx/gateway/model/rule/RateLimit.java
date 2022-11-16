package com.nginx.gateway.model.rule;

import lombok.Builder;
import lombok.Data;

/**
 * <p>请求限流限速策略</p>
 * <p>
 * limit_req_zone $binary_remote_addr zone=perip:10m rate=1r/s;
 * limit_req_zone $server_name zone=perserver:10m rate=10r/s;
 * <p>
 * server {
 * ...
 * limit_req zone=perip burst=5 nodelay;
 * limit_req zone=perserver burst=10;
 * }
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class RateLimit {

    /**
     * 计数器
     */
    private String key;

    /**
     * 计数器内存空间名称
     */
    private String zone_name;

    /**
     * 计数器内存空间大小
     */
    private String zone_size;

    /**
     * 请求速率
     */
    private String rate;

    /**
     * 流量突发缓冲值
     */
    private Integer burst;

    /**
     * 是否减少限流带来的过多延迟处理
     */
    private Boolean nodelay;

    /**
     * 设定延迟请求数量，默认为0，表示被限流的请求都延迟
     */
    private Integer delay_number;

    /**
     * 限流限速日志级别
     */
    private String log_level;
}
