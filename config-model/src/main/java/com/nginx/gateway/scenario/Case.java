package com.nginx.gateway.scenario;

import lombok.Data;

/**
 * <p>Case</p>
 *
 * @author Jiuping Yi
 */
@Data
public class Case {
    private String name;
    private Request request;
    private Response response;
}
