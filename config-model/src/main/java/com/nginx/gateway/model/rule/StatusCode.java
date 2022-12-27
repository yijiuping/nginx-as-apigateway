package com.nginx.gateway.model.rule;

import lombok.Data;

import java.util.List;

/**
 * <p>响应状态码标准定义</p>
 *
 * @author Jiuping Yi
 */
@Data
public class StatusCode {

    /**
     * 状态码列表
     */
    private List<Status> statuses;

    @Data
    public static class Status {
        /**
         * 响应码
         */
        private Integer code;

        /**
         * 响应消息
         */
        private String message;
    }

}
