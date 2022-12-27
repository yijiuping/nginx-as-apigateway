package com.nginx.gateway.model;

import com.nginx.gateway.model.common.Key;
import lombok.Data;

import java.util.List;

/**
 * <p>上游服务集群</p>
 *
 * @author Jiuping Yi
 */
@Data
@Key(format = "/gateways/<gateway_id>/http/servers/<server_id>/upstreams/<upstream_id>")
public class Upstream {

    private String id;

    private String gateway_id;

    private String server_id;

    /**
     * 上游集群名称
     */
    private String name;

//    /**
//     * 是否为流量镜像目的地上游集群
//     */
//    private Boolean is_mirror;
//
//    /**
//     * 是否为灰度发布/蓝绿发布上游集群
//     */
//    private Boolean is_canary;

    /**
     * 上游集群实例集合
     */
    private List<Member> members;

    /**
     * 上游集群策略
     */
    private UpstreamPolicy policy;

    public String key() {
        return String.format("/gateways/%s/http/servers/%s/upstreams/%s", gateway_id, server_id, id);
    }

}

