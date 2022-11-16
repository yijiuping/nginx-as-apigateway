package com.nginx.gateway.model.rule;

import lombok.Builder;
import lombok.Data;

/**
 * <p>日志输出策略</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class Logging {

    private AccessLog access_log;

    private ErrorLog error_log;

    /**
     * 访问日志策略
     */
    @Builder
    @Data
    public static class AccessLog {
        /**
         * 访问日志输出格式
         */
        private String access_log_format;

        /**
         * 日志输出协议：FILE, UDP
         */
        private String access_log_protocol;

        /**
         * 本地文件输出时的日志路径
         */
        private String access_log_file_path;

        /**
         * 日志服务器输出时的日志服务器地址
         */
        private String access_log_server_addr;

        /**
         * 日志服务器输出时的日志服务器端口
         */
        private String access_log_server_port;
    }

    /**
     * 错误日志策略
     */
    @Builder
    @Data
    public static class ErrorLog {

        /**
         * 日志服务器输出时的日志服务器端口
         */
        private String error_log_level;

        /**
         * 日志服务器输出时的日志服务器端口
         */
        private String error_log_protocol;

        /**
         * 日志服务器输出时的日志服务器端口
         */
        private String error_log_file_path;

        /**
         * 日志服务器输出时的日志服务器端口
         */
        private String error_log_server_addr;

        /**
         * 日志服务器输出时的日志服务器端口
         */
        private String error_log_server_port;
    }
}
