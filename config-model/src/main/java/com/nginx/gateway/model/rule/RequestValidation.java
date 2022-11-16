package com.nginx.gateway.model.rule;

import lombok.Builder;
import lombok.Data;

/**
 * <p>请求校验规则</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class RequestValidation {

    /**
     * 允许的HTTP Method
     */
    private String[] allow_methods;

    /**
     * 对请求Body做json格式校验
     */
    private Boolean json_body_check;

    /**
     * 对请求做最大长度校验
     */
    private String client_max_body_size = "16k";

}
