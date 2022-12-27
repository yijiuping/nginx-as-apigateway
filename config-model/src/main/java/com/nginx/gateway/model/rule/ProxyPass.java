package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>请求反向代理策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class ProxyPass {

    /**
     * 条件为空，或者action没填时，都表示路由到上游服务集群
     */
    private String proxy_upstream;
}
