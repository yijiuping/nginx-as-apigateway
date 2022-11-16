package com.nginx.gateway.model.rule;

import com.nginx.gateway.model.common.Condition;
import lombok.Builder;
import lombok.Data;

/**
 * <p>请求流量调度分流规则</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class TrafficRoute {

    /**
     * 是否为按比例分流，按比例分流时比例条件必填
     */
    private Boolean by_ratio;

    /**
     * 按比例分流条件
     */
    private RatioRule[] ratio_rules;

    /**
     * 流量调度规则集合，基于条件匹配分流
     */
    private ConditionRule[] condition_rules;

    /**
     * 默认上游服务，当条件为空，或者条件不匹配时，默认的action为PROXY
     */
    private String default_upstream;

    @Data
    public static class RatioRule {

        /**
         * 按比例路由的比例值，与conditions只能二选一
         */
        private Double ratio;

        /**
         * 条件为空，或者action没填时，都表示路由到上游服务集群
         */
        private ProxyPass proxy;
    }

    @Data
    public static class ConditionRule {

        /**
         * 路由条件数
         */
        private Condition condition;

        /**
         * 条件为空，或者action没填时，都表示路由到上游服务集群
         */
        private ProxyPass proxy;
    }
}
