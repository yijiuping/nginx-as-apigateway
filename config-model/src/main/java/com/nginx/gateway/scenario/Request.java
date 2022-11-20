package com.nginx.gateway.scenario;

import lombok.Data;

import java.util.Map;

/**
 * <p>Request</p>
 *
 * @author Jiuping Yi
 */
@Data
public class Request {
    private String scheme = "http";
    private String port = "80";
    private String method = "GET";
    private String uri;
    private Map<String, String> headers;
    private String body;

    private String basic_auth_username;
    private String basic_auth_password;
}
