package com.nginx.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * <p>Response</p>
 *
 * @author Jiuping Yi
 */
@Data
@AllArgsConstructor
public class Response {
    private String status;
    private Map<String, String> headers;
    private String body;
}
