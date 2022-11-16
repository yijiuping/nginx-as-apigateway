package com.nginx.gateway.model;

import lombok.Builder;
import lombok.Data;

/**
 * <p>上游服务集群实例</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class Member {

    /**
     * 后端服务地址
     */
    private String address;

    /**
     * 权重
     */
    private Integer weight;
}
