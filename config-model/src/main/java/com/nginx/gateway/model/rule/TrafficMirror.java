package com.nginx.gateway.model.rule;

import com.nginx.gateway.model.common.Header;
import lombok.Data;

/**
 * <p>请求流量镜像策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class TrafficMirror {

    /**
     * 是否开启流量镜像，可选值有：off、on
     */
    private String mirror = "off";

    /**
     * 镜像的URI地址，每个Server上下文唯一
     */
    private String mirror_location;

    /**
     * 是否镜像客户端的请求体，可选值有：off、on
     */
    private String mirror_request_body = "on";

    /**
     * 流量被镜像到的上游服务，需要带上"http://"，如：http://backend_mirror
     */
    private String mirror_upstream;

    /**
     * TODO: update template
     * 流量被镜像到的上游服务地址，不填使用原地址，即：$request_uri
     */
    private String proxy_pass_uri;

    /**
     * TODO: update template
     * 设置流量镜像时的Header
     */
    private Header headers;
}
