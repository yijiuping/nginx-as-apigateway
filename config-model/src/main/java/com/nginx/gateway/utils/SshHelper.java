package com.nginx.gateway.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>SshHelper</p>
 *
 * @author Jiuping Yi
 */
public class SshHelper {

    public static SshResponse runCommand(SshConnection conn, String cmd, long timeout) throws IOException {
        SshClient client = SshClient.setUpDefaultClient();

        try {
            // Open the client
            client.start();

            // Connect to the server
            ConnectFuture cf = client.connect(conn.getUsername(), conn.getHostname(), 22);
            ClientSession session = cf.verify().getSession();
            session.addPasswordIdentity(conn.getPassword());
            session.auth().verify(TimeUnit.SECONDS.toMillis(timeout));

            // Create the exec and channel its output/error streams
            ChannelExec ce = session.createExecChannel(cmd);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayOutputStream err = new ByteArrayOutputStream();
            ce.setOut(out);
            ce.setErr(err);

            // Execute and wait
            ce.open();
            Set<ClientChannelEvent> events = ce.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), TimeUnit.SECONDS.toMillis(timeout));
            session.close(false);

            // Check if timed out
            if (events.contains(ClientChannelEvent.TIMEOUT)) {
                throw new RuntimeException(conn.getHostname() + " 命令 " + cmd + "执行超时 " + timeout);
            }

            return new SshResponse(out.toString(), err.toString(), ce.getExitStatus());

        } finally {
            client.stop();
        }
    }

    @Data
    @AllArgsConstructor
    public static class SshConnection {
        private String hostname;
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class SshResponse {
        private String stdOutput;
        private String errOutput;
        private Integer returnCode;
    }
}

