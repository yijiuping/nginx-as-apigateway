package com.nginx.gateway.model.rule;

import com.nginx.gateway.model.common.Header;
import lombok.Data;

import java.util.List;

/**
 * <p>上游服务集群主动健康检查策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class HealthCheck {

    /**
     * 服务端用于健康检查的URI，默认是"/"
     */
    private String uri;

    /**
     * 健康检查的周期，默认是5秒
     */
    private Integer interval = 5;

    /**
     * 健康检查随机延迟上限，默认是0，表示不做延迟，大于零表示最近延迟该值以内的时间做检查
     */
    private String jitter = "0";

    /**
     * 检查失败多少次后，服务实例被认为不可用，默认为1次
     */
    private Integer fails = 1;

    /**
     * 检查成功多少次以后，服务实例被认为可用，默认为1次
     */
    private Integer passes = 1;

    /**
     * 用于健康检查的服务实例端口
     */
    private Integer port = 80;

    /**
     * 用于校验健康检查返回值的本地match块名称，默认值为：health_status
     */
    private String match = "health_status";

    /**
     * 健康检查支持长连接
     */
    private String keepalive_time = "1m";

    /**
     * 被检查后端服务连接超时时间
     */
    private String proxy_connect_timeout = "60s";

    /**
     * 被检查后端服务读取超时时间
     */
    private String proxy_read_timeout = "60s";

    /**
     * 被检查后端服务发送超时时间
     */
    private String proxy_send_timeout = "60s";

    /**
     * 检查时设置的请求头信息
     */
    private List<Header.Set> headers;
}
