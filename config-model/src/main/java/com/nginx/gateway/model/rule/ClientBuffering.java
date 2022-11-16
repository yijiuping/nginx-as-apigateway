package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>客户端请求缓冲策略</p>
 *
 * @author Jiuping Yi
 */
//@Builder
@Data
public class ClientBuffering {

    /**
     * 是否开启客户端请求缓冲功能，默认值为 on。当开启了缓冲功能后，NGINX会先把完整的请求都收完后才开始发送到服务端。
     * 如果关闭则表示直接发送给服务端，此时如果接收的服务端实例出故障，无法再做故障转移。
     */
    private String proxy_request_buffering = "on";

    /**
     * 设置用于读取客户端请求头的缓冲区大小，默认值为1K，大部分时候都不用修改，但是如果携带了较长的头属性（例如：Cookie），
     * 则可能超过该值。如果请求行或请求字段超过该值，会使用large_client_header_buffers定义的更大的缓冲区值。
     */
    private String client_header_buffer_size = "1k";

    /**
     * 设置大请求头的缓冲区数量和值，默认4个缓冲区，每个8K的空间。一个请求行不能超过一个缓冲区的大小，超过则直接返回414错误（Request-URI Too Large）。
     * 请求字段也不能超过缓冲区大小，超过则直接返回400错误（Bad Request）。缓冲区按需分配，当请求处理结束后连接转为保活状态时会释放缓冲区。
     */
    private String large_client_header_buffers = "4 8k";

    /**
     * 设置用于读取客户端请求体的缓冲区大小，32位系统默认值为8K，64位为16K。当请求体超过缓冲区大小时，会将全量请求或超过部分写入临时文件，
     * 因为有文件IO对性能有一定影响。如果大部分请求都较大，且并发较少时可以适当加大缓冲区的值，避免文件IO。
     */
    private String client_body_buffer_size = "16k";

    /**
     * 置用于存放客户端请求超过缓冲区后写临时文件的根目录，默认路径是：/var/cache/nginx/client_temp，需要确保磁盘的空间与读写性能，
     * 文件描述符的数量设置足够，避免写入失败，导致请求失败的情况。
     */
    private String client_body_temp_path;
}
