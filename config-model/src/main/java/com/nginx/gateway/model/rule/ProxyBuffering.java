package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>上游服务缓冲策略</p>
 *
 * @author Jiuping Yi
 */
//@Builder
@Data
public class ProxyBuffering {

    /**
     * 是否开启服务端响应缓冲区功能，默认开启。开启后，服务端的响应数据会先写入缓冲区（缓冲区大小由proxy_buffer_size 和 proxy_buffers设置），
     * 如果缓冲区写满会将剩余部分写入临时文件。关闭缓冲区则表示NGINX采用同步传输的方式，不接收完服务端响应就直接发给客户端。
     * 客户端也可通过设置X-Accel-Buffering请求头来开启或关闭该功能。
     */
    private String proxy_buffering = "on";

    /**
     * 设置接收服务端响应的首包数据缓冲区大小，默认为一个内存页大小（32位机器为4K，64位机器则为8K）。
     * 通常这部分数据仅包含少量的响应头信息，较小的值即可满足。
     */
    private String proxy_buffer_size = "8k";

    /**
     * 设置接收服务端响应数据的缓冲区个数及空间大小，默认为8个缓冲区，一个缓冲区大小默认为一个内存页大小（32位机器为4K，64位机器则为8K）。
     * 响应数据会先写如缓冲区，如果开启了缓冲功能，写满以后会将剩余部分继续写入临时文件。为了避免响应数据较大而频繁写磁盘，可适当的调大该缓冲区大小。
     */
    private String proxy_buffers = "8 8k";

    /**
     * 当开启了服务端响应缓冲区功能后，响应数据先写缓冲区，通过该值可指定不必等数据全都接收完才发送给客户端，
     * 而是当缓冲数据接收够该值后就开始发送，确保客户端接收的低延时。
     */
    private String proxy_busy_buffers_size = "16k";

    /**
     * 设置用于存放服务端响应超过缓冲区后写临时文件的根目录，默认路径是：/var/cache/nginx/proxy_temp，需要确保磁盘的空间与读写性能，
     * 文件描述符的数量设置足够，避免写入失败，导致请求失败的情况。
     */
    private String proxy_temp_path;

    /**
     * 设置每次临时文件写入的数据量最大值，由于响应内容是先写缓冲区，再从缓冲区写入磁盘，因此该值受限于两个缓冲区的和，
     * 即：proxy_buffer_size和proxy_buffers，默认值是8K或16K。
     */
    private String proxy_temp_file_write_size = "16k";

    /**
     * 设置临时文件的最大大小，虽然可多次写入临时文件，但是总文件大小受此控制。默认值为1024MB。另外，如果开启了缓存（proxy_cache）功能，
     * 文件写入则不受此限制；配置成0表示禁止将响应写入临时文件。
     */
    private String proxy_max_temp_file_size = "1024m";

}
