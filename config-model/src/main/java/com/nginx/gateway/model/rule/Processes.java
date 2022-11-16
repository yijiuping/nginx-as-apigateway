package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>网关全局主、工作进程策略配置</p>
 *
 * @author Jiuping Yi
 */
//@Builder
@Data
public class Processes {

    /**
     * 工作进程上下游最大连接数
     */
    private String worker_connections = "65535";

    /**
     * 工作进程CPU亲和性配置
     */
    private String worker_cpu_affinity = "auto";

    /**
     * 工作进程数量，默认为auto，即按照CPU核心数创建进程数
     */
    private String worker_processes = "auto";

    /**
     * 工作进程最大File Descriptor数
     */
    private Integer worker_rlimit_nofile = 655350;

    /**
     * 工作进程优雅停机超时时间
     */
    private String worker_shutdown_timeout;

    /**
     * 是否开启服务基本信息查询
     */
    private String server_tokens = "off";
}
