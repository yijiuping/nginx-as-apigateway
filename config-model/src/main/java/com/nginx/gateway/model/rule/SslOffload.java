package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>SSL/TLS卸载策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class SslOffload {

    /**
     * SSL/TLS端口
     */
    private Integer https_port = 443;

    /**
     * 是否使用http2
     */
    private String http2 = "off";

    /**
     * 证书文件路径
     */
    private String ssl_certificate = "/etc/nginx/ssl/example.com.crt";

    /**
     * 证书密钥文件路径
     */
    private String ssl_certificate_key = "/etc/nginx/ssl/example.com.key";

    /**
     * SSL/TLS会话缓存区域及大小
     */
    private String ssl_session_cache = "shared:SSL:10m";

    /**
     * SSL/TLS会话缓存超时时间
     */
    private String ssl_session_timeout = "5m";

    /**
     * SSL/TLS加密套件
     */
    private String ssl_ciphers = "HIGH:!aNULL:!MD5";

    /**
     * SSL/TLS加密协议
     */
    private String ssl_protocols = "TLSv1.2 TLSv1.3";

    /**
     * 是否拒绝握手
     */
    private Boolean reject_handshake;
}
