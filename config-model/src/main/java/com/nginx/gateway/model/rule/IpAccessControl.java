package com.nginx.gateway.model.rule;

import lombok.Data;

import java.util.List;

/**
 * <p>基于客户端IP地址的访问控制</p>
 *
 * @author Jiuping Yi
 */
@Data
public class IpAccessControl {

    /**
     * 按顺序执行deny/allow的地址策略
     */
    private List<Item> items;

    @Data
    public static class Item {

        /**
         * 支持CIDR格式
         */
        private String address;

        /**
         * deny, allow
         */
        private String type;
    }

}
