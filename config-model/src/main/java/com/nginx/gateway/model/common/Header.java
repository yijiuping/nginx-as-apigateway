package com.nginx.gateway.model.common;

import lombok.Builder;
import lombok.Data;

/**
 * <p>响应头操作</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class Header {

    /**
     * 添加新的响应头。格式为 ["name: value", ...]。这个值能够以 $var 的格式包含 NGINX 变量，比如 $remote_addr $balancer_ip。
     */
    private Add[] add;

    /**
     * 改写响应头。格式为 {"name": "value", ...}。这个值能够以 $var 的格式包含 NGINX 变量，比如 $remote_addr $balancer_ip。
     */
    private Set[] set;

    /**
     * 移除响应头。格式为 ["name", ...]。
     */
    private String[] remove;

    @Builder
    @Data
    public static class Add {
        private String name;
        private String value;
        private Boolean always;
    }

    @Builder
    @Data
    public static class Set {
        private String name;
        private String value;
    }
}
