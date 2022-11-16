package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>客户端连接控制策略</p>
 *
 * @author Jiuping Yi
 */
//@Builder
@Data
public class ClientConn {

    private Integer keepalive_requests = 1000;
    private String keepalive_timeout = "75s";
    private String keepalive_time = "1h";
    private Integer client_header_timeout = 60;
    private Integer client_body_timeout = 60;
    private String reset_timedout_connection = "on";
    private Integer send_timeout = 60;

}
