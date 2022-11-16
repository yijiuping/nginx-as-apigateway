package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>上游服务连接策略</p>
 *
 * @author Jiuping Yi
 */
//@Builder
@Data
public class UpstreamConn {

    //=========================上游服务长连接策略==========================//

    /**
     * 设置每个worker进程对上游服务器集群保持的最大空闲连接数，它不影响新连接的创建，当没有长连接可用会新建连接，
     * 当空闲连接数超过此值时，会把最不常用的连接关闭，直到等于该值。另外，连接数量是对后端所有服务器的连接，而不是某台的。
     */
    private Integer keepalive = 32;

    /**
     * NINGX与服务端使用长连接时，超过指定数量的请求后，该连接会被NGINX主动关闭。默认值1000，如果服务端较少，吞吐量确较大的场景可以调大该值；
     */
    private Integer keepalive_requests = 1000;

    /**
     * 限制长连接的最长连接时间
     */
    private String keepalive_time = "1h";

    /**
     * NGINX与服务端使用长连接时，超过指定的连接保持时间后，该连接会被NGINX主动关闭。默认值60秒，如果服务端较少，吞吐量确较大的场景可以调大该值；
     */
    private String keepalive_timeout = "60s";

    //=========================Member 通用策略==========================//

    /**
     * 上游集群实例不可用超时时间
     */
    private Integer fail_timeout = 5;

    /**
     * 最大异常次数后被认为不可用
     */
    private Integer max_fails = 3;

    /**
     * 上游集群实例最大连接数
     */
    private Integer max_conns = 0;
}
