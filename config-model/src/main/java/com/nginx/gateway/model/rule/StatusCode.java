package com.nginx.gateway.model.rule;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * <p>响应状态码标准定义</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class StatusCode {

    private List<Status> statuses;

    @Builder
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
