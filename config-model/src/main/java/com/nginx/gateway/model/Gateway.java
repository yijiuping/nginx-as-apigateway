package com.nginx.gateway.model;

import com.nginx.gateway.model.common.Key;
import lombok.Builder;
import lombok.Data;

/**
 * <p>网关集群</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
@Key(format = "/gateways/<gateway_id>")
public class Gateway {

    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 网关集群实例通用策略
     */
    private GatewayPolicy policy;

    public String key() {
        return "/gateways/" + id;
    }
}
