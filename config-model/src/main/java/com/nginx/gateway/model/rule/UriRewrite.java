package com.nginx.gateway.model.rule;

import lombok.Data;

import java.util.List;

/**
 * <p>请求URI重写策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class UriRewrite {

    /**
     * URI 重写映射规则集合
     */
    private List<UriMap> uri_maps;

    /**
     * 是否开启重写日志
     */
    private String rewrite_log = "off";

    @Data
    public static class UriMap {
        private String from;
        private String to;
    }
}
