package com.nginx.gateway.model.rule;

import lombok.Builder;
import lombok.Data;

/**
 * <p>运维监控相关策略</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class Management {

    /**
     * 管理端口
     */
    private Integer mgmt_port = 8859;

    /**
     * 是否开启NGINX Plus的dashboard
     */
    private String mgmt_dashboard = "on";

    /**
     * 是否开启Prometheus指标拉取地址
     */
    private String mgmt_metrics = "on";

    /**
     * 是否开启健康检查地址
     */
    private String mgmt_health = "on";

    /**
     * 是否开启API
     */
    private String mgmt_api = "on";

    /**
     * 客户端IP访问控制白名单
     */
    private String[] mgmt_allow_cidrs;
}
