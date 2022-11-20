package com.nginx.gateway.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <p>GatewayConfig</p>
 *
 * @author Jiuping Yi
 */
@Configuration
@Data
public class GatewayConfig {

    @Value("${etcd.endpoints: http://127.0.0.1:2379}")
    private String etcdEndpoints;

    @Value("${ssh.host: 127.0.0.1}")
    private String sshHost;

    @Value("${ssh.user: root}")
    private String sshUser;

    @Value("${ssh.passwd: admin}")
    private String sshPasswd;

}
