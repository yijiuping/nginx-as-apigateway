package com.nginx.gateway.model;

import com.nginx.gateway.model.common.Header;
import com.nginx.gateway.model.rule.*;
import lombok.Data;

import java.util.Map;

/**
 * <p>API分组或分域下API的通用策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class LocationGroupPolicy {

    /**
     * 日志记录/输出策略
     */
    private Logging logging;

    //=========================请求相关策略==========================//

    /**
     * URI重写策略
     */
    private UriRewrite uri_rewrite;

    /**
     * API Key认证策略
     */
    private String api_key_auth;

    /**
     * Basic认证策略
     */
    private BasicAuth basic_auth;

    /**
     * 请求校验策略
     */
    private RequestValidation request_valid;

    /**
     * 请求限流策略
     */
    private RateLimit rate_limit;

    /**
     * 请求头的新增、删除、修改，支持变量，存在则替换，不存在则添加，删除使用空值""
     */
    private Map<String, String> proxy_headers;

    //=========================响应相关策略==========================//

    /**
     * 响应压缩策略
     */
    private Compression compression;

    /**
     * 响应头的增加、删除、修改
     */
    private Header response_headers;

}
