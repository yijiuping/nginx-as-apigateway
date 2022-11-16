package com.nginx.gateway.model.rule;

import com.nginx.gateway.model.common.LBAlgorithmEnum;
import lombok.Data;

/**
 * <p>上游服务集群的负载均衡策略</p>
 *
 * @author Jiuping Yi
 */
//@Builder
@Data
public class LoadBalance {

    /**
     * 负载均衡算法，默认为：Round Robin
     */
    private LBAlgorithmEnum algorithm = LBAlgorithmEnum.RANDOM;

    /**
     * Hash和一致性hash算法时的key
     */
    private String hash_key="$remote_addr";

}
