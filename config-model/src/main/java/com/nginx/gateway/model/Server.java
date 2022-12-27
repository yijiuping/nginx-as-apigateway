package com.nginx.gateway.model;

import com.nginx.gateway.model.common.Key;
import lombok.Data;

/**
 * <p>HTTP虚拟服务定义</p>
 *
 * @author Jiuping Yi
 */
@Data
@Key(format = "/gateways/<gateway_id>/http/servers/<server_id>")
public class Server {

    private String id;

    private String gateway_id;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 服务监听端口
     */
    private Integer port;

    /**
     * 服务通用策略
     */
    private ServerPolicy policy;

    public String key() {
        return String.format("/gateways/%s/http/servers/%s", gateway_id, id);
    }

}
