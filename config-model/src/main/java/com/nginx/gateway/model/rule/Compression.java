package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>上游响应压缩策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class Compression {

    /**
     * 是否开启压缩
     */
    private String gzip = "off";

    /**
     * 设置开启压缩的最小响应内容长度，基于“Content-Length”判断，默认值为20字节，可以设置稍大的值平衡传输效率与CPU消耗；
     */
    private String gzip_min_length = "1k";

    /**
     * 用于缓冲压缩数据流的缓冲区，默认分配缓冲区大小为一个内存页，即：32个4K 或 16个8K，取决于不同的平台
     */
    private String gzip_buffers = "16 64k";

    /**
     * 设置支持压缩的http最小版本，默认是1.1
     */
    private String gzip_http_version = "1.1";

    /**
     * 压缩比，默认值为1，最大为9；越大表示压缩比越高，CPU消耗也越大
     */
    private Integer gzip_comp_level = 4;

    /**
     * 支持压缩的内容类型，默认为text/html
     */
    private String gzip_types = "text/plain application/x-javascript text/css application/xml application/json";

    /**
     * 开启或关闭插入响应头字段“Vary: Accept-Encoding”
     */
    private String gzip_vary = "on";

    /**
     * 基于正则表达式匹配User-Agent属性来判断是否禁用压缩
     */
    private String gzip_disable = "msie6";

    /**
     * 是否开启为代理服务的响应压缩功能，NGINX作为代理服务器会给转发到服务端的请求增加Via请求头字段，响应回来的请求同也会包含；根据Via请求头及该设置来判断是否压缩；
     * 选项说明：
     * off - 关闭所有的代理结果数据的压缩
     * expired - 启用压缩，如果header头中包含 "Expires" 头信息
     * no-cache - 启用压缩，如果header头中包含 "Cache-Control:no-cache" 头信息
     * no-store - 启用压缩，如果header头中包含 "Cache-Control:no-store" 头信息
     * private - 启用压缩，如果header头中包含 "Cache-Control:private" 头信息
     * no_last_modified - 启用压缩,如果header头中不包含 "Last-Modified" 头信息
     * no_etag - 启用压缩 ,如果header头中不包含 "ETag" 头信息
     * auth - 启用压缩 , 如果header头中包含 "Authorization" 头信息
     * any - 无条件启用压缩
     */
    private String gzip_proxied = "any";
}
