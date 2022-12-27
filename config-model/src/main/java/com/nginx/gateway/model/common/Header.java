package com.nginx.gateway.model.common;

import lombok.Data;

/**
 * <p>请求或响应头操作</p>
 *
 * @author Jiuping Yi
 */
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

    /**
     * 新增头操作
     */
    @Data
    public static class Add {
        /**
         * 头名称
         */
        private String name;

        /**
         * 头值
         */
        private String value;

        /**
         * 是否总是添加
         */
        private Boolean always;
    }

    /**
     * 更新头操作
     */
    @Data
    public static class Set {
        /**
         * 头名称
         */
        private String name;

        /**
         * 头值
         */
        private String value;
    }
}
