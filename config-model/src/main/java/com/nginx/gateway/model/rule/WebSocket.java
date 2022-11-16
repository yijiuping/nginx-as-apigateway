package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>WebSocket启用策略</p>
 *
 * @author Jiuping Yi
 */
//@Builder
@Data
public class WebSocket {

    /**
     * 是否开启WebSocket协议
     */
    private String web_socket_switch = "off";

}
