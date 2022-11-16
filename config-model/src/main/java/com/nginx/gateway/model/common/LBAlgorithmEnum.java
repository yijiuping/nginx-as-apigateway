package com.nginx.gateway.model.common;

/**
 * <p>负载均衡算法</p>
 *
 * @author Jiuping Yi
 */
public enum LBAlgorithmEnum {
    ROUND_ROBIN, // 默认策略
    RANDOM, // 随机
    IP_HASH, // 基于客户端IP的Hash
    HASH,    // 基于指定属性的Hash
    CONSISTENT_HASH, // 一致性Hash
    LEAST_TIME, // 最小平均响应时间
    LEAST_CONN // 最小连接数
}
