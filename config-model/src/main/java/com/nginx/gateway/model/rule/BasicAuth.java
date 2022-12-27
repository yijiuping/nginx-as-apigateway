package com.nginx.gateway.model.rule;

import lombok.Data;

/**
 * <p>HTTP Basic认证策略</p>
 *
 * @author Jiuping Yi
 */
@Data
public class BasicAuth {

    /**
     * 配置提示登录的信息
     */
    private String auth_basic = "Please input Username and Password";

    /**
     * 用户名和密码配置文件地址
     */
    private String auth_basic_user_file = "/etc/nginx/conf.d/htpasswd";

}
