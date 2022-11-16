package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>会话保持策略</p>
 *
 * @author Jiuping Yi
 */
//@Builder
@Data
public class SessionSticky {

    /**
     * 会话保持策略定义，开源NGINX默认支持IP_HASH
     */
    private String sticky = "ip_hash";

}
