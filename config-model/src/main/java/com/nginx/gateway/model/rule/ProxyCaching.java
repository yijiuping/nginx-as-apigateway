package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>上游响应缓存策略</p>
 *
 * @author Jiuping Yi
 */
//@Builder
@Data
public class ProxyCaching {

    /**
     * 是否开启缓存
     */
    private String proxy_cache_switch = "off";

    /**
     * 缓存的Key Zone名称
     */
    private String proxy_cache_key_zone = "api_cache";

    /**
     * 缓存Key Zone的内存空间大小
     */
    private String proxy_cache_key_zone_size = "10m";

    /**
     * 缓存的最大缓存空间占用
     */
    private String proxy_cache_max_size = "10g";

    /**
     * 缓存有效时长
     */
    private String proxy_cache_duration = "60m";

    /**
     * 缓存的文件目录
     */
    private String proxy_cache_path = "/var/cache/nginx/proxy_cache levels=1:2";

    /**
     * 可启用使用具有 `If-Modified-Since` 和 `If-None-Matc` 头字段的条件请求来重新验证过期的缓存项。
     */
    private String proxy_cache_revalidate = "on";

    /**
     * 设置在收到多次相同请求后将缓存此请求的响应，即自动识别热点请求
     */
    private Integer proxy_cache_min_uses = 3;

    /**
     * 设置在什么条件下可用过期的缓存，默认是关闭的。但是对于内容实效性不是特别严格时，可以开启避免后端服务异常或缓存正在更新时无内容响应给客户端
     */
    private String proxy_cache_use_stale = "error timeout updating http_500 http_502 http_503 http_504";

    /**
     * 当开启了后端响应内容缓存后，可开启一个子请求来更新缓存内容。需要考虑客户端请求内容时，正好子请求在更新缓存的情况，
     * 如果允许更新时使用过期内容，需要开启`proxy_cache_use_stale updateing;`开关。
     */
    private String proxy_cache_background_update = "on";

    /**
     * 启用后，将以缓存Key为锁，一次仅允许一个请求到服务端获取新的响应用于缓存。其他请求相同缓存Key的请求则等待，直到可以获取到缓存内容，
     * 或者等待超时`proxy_cache_lock_timeout`为止。
     */
    private String proxy_cache_lock = "on";

    /**
     * 设置不同的响应码的缓存时间，例如：200缓存10分钟，400缓存1分钟；
     */
    private String proxy_cache_valid = "any 10m";

    /**
     * 缓存的Key结构
     */
    private String proxy_cache_key = "$scheme$proxy_host$request_uri";

//    proxy_ignore_headers X-Accel-Expires Expires Cache-Control Set-Cookie;
//    add_header X-Proxy-Cache $upstream_cache_status;
}
