package com.nginx.gateway.model;

import com.nginx.gateway.model.common.Key;
import lombok.Data;

/**
 * <p>API分组或分域定义</p>
 *
 * @author Jiuping Yi
 */
@Data
@Key(format = "/gateways/<gateway_id>/http/servers/<server_id>/groups/<group_id>")
public class LocationGroup {

    private String id;

    private String gateway_id;

    private String server_id;

    /**
     * API的URI地址，支持精确配置与正则配置
     */
    private String uri;

    /**
     * API分组或分域下API的通用策略
     */
    private LocationGroupPolicy policy;

    public String key() {
        return String.format("/gateways/%s/http/servers/%s/groups/%s", gateway_id, server_id, id);
    }
}
