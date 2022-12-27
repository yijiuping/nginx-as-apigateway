package com.nginx.gateway.model;

import com.nginx.gateway.model.common.Key;
import lombok.Data;

/**
 * <p>单个API定义</p>
 *
 * @author Jiuping Yi
 */
@Data
@Key(format = "/gateways/<gateway_id>/http/servers/<server_id>/groups/<group_id>/locations/<location_id>")
public class Location {

    private String id;

    private String gateway_id;

    private String server_id;

    private String group_id;

    /**
     * API的URI地址，支持精确匹配和正则匹配
     */
    private String uri;

    /**
     * 单个API级别策略
     */
    private LocationPolicy policy;

    public String key() {
        return String.format("/gateways/%s/http/servers/%s/groups/%s/locations/%s", gateway_id, server_id, group_id, id);
    }
}
