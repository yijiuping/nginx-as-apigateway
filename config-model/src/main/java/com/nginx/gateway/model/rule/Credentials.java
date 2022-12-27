package com.nginx.gateway.model.rule;

import lombok.Data;

import java.util.List;

/**
 * <p>Credentials</p>
 *
 * @author Jiuping Yi
 */
@Data
public class Credentials {

    /**
     * 用户名和密码对，用于生成用户名和密码文件内容
     */
    private List<User> users;

    @Data
    public static class User {
        private String username;
        private String password;
    }
}
