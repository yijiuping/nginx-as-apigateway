package com.nginx.gateway.model.rule;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * <p>API Key认证策略</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class ApiKeyAuth {

    /**
     * 获取API Key值的方式，默认从请求头中取
     */
    private ApiKeyInputTypeEnum input_type = ApiKeyInputTypeEnum.HEADER;

    /**
     * 获取API Key值的头或查询参数名称
     */
    private String api_key_name = "x-api-key";

    /**
     * 当获取不到API Key时，默认设置的API Key值
     */
    private String default_key = "";

    /**
     * 定义的API Key映射表，Map的Key为客户端ID，Map的值为API Key值，如：
     * client_one:7B5zIqmRGXmrJTFmKa99vcit
     */
    private List<ApiKey> api_keys;

    /**
     * API Key的读取方式
     */
    public enum ApiKeyInputTypeEnum {
        HEADER, // 从请求头获取
        ARGUMENT // 从Query Param中获取
    }

    @Builder
    @Data
    public static class ApiKey {
        private String key;
        private String secret;
    }
}
