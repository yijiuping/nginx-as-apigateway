package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>请求重定向策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class Redirect {

    /**
     * SSL/TLS重定向
     */
    private Boolean ssl_redirect = Boolean.TRUE;

}
