package com.nginx.gateway.model.rule;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * <p>Credentials</p>
 *
 * @author Jiuping Yi
 */
@Builder
@Data
public class Credentials {

    /**
     * 用户名和密码对，用于生成用户名和密码文件内容
     */
    private List<User> users;

    @Builder
    @Data
    public static class User {
        private String username;
        private String password;
    }
}
