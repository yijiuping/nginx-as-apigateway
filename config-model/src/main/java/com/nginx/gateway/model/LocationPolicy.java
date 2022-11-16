package com.nginx.gateway.model;

import com.nginx.gateway.model.common.Header;
import com.nginx.gateway.model.rule.*;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * <p>单个API级别策略</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class LocationPolicy {

    //=========================请求相关策略==========================//

    /**
     * 请求校验策略
     */
    private RequestValidation request_valid;

    /**
     * 请求限流策略
     */
    private RateLimit rate_limit;

    /**
     * 请求流量镜像策略
     */
    private TrafficMirror traffic_mirror;

    /**
     * 请求流量路由策略
     */
    private TrafficRoute traffic_route;

    /**
     * 请求头的新增、删除、修改，支持变量，存在则替换，不存在则添加，删除使用空值""
     */
    private Map<String, String> proxy_headers;

    /**
     * 是否开启WebSocket
     */
    private WebSocket web_socket;

    //=========================响应相关策略==========================//

    /**
     * 响应缓存策略
     */
    private ProxyCaching proxy_caching;

    /**
     * 响应压缩策略
     */
    private Compression compression;

    /**
     * 响应头的增加、删除、修改
     */
    private Header response_headers;

}
