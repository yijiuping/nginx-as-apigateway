package com.nginx.gateway;

import com.google.gson.Gson;
import com.nginx.gateway.model.*;
import com.nginx.gateway.scenario.*;
import com.nginx.gateway.service.EtcdService;
import com.nginx.gateway.service.GatewayConfig;
import com.nginx.gateway.utils.CaseHelper;
import com.nginx.gateway.utils.SshHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.ssl.SSLContexts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nginx.gateway.utils.SshHelper.runCommand;

/**
 * <p>DataDrivenTest</p>
 *
 * @author Jiuping Yi
 */
@Slf4j
@SpringBootTest
public class DataDrivenTest {

    private static final Gson gson = new Gson();

    @Resource
    private EtcdService etcdService;

    @Resource
    private GatewayConfig config;

    @ParameterizedTest
    @ValueSource(strings = {
            "generic/simple",
            "generic/client_conn",
            "auth/basic",
            "auth/apikey",
            "access_control/ip_blacklist",
            "access_control/ip_whitelist",
            "compression/location_gzip_on",
            "compression/group_gzip_on",
            "proxy_cache/location_cache_on",
            "conn_limit/limit_1",
            "load_balance/algorithm",
            "mgmt/enable_mgmt",
            "mgmt/disable_mgmt",
            "rate_limit/per_group",
            "rate_limit/per_location",
            "request_valid/per_group",
            "request_valid/per_location",
            "status_code/status_msg",
            "tls/ssl_offload",
            "traffic_mirror/mirror",
            "traffic_route/route_by_ratio",
            "traffic_route/route_by_arg",
            "traffic_route/route_by_header",
            "traffic_route/route_by_var",
            "traffic_route/route_by_cookie",
            "uri_rewrite/uri_exact",
    })
    public void testGateway(String test) throws Exception {

        // Parse scenario config
        Scenario scenario = parseScenario(test);

        // Clear etcd history config
        clearGatewayConfig();

        // Write etcd config
        writeGatewayConfig(test);

        // Do pre-test action
        executePreAction(scenario);

        Thread.sleep(500);

        // Run cases
        runCases(scenario);

        // Do post-test action
        executePostAction(scenario);
    }

    private void runCases(Scenario scenario) throws Exception {

        if (scenario.getCases() == null) {
            return;
        }

        for (Case aCase : scenario.getCases()) {
            Response response = runCase(aCase.getRequest());
            Assertions.assertNotNull(response);
            Assertions.assertEquals(aCase.getResponse().getStatus(), response.getStatus());

            if (aCase.getResponse().getHeaders() != null) {
                Assertions.assertNotNull(response.getHeaders());

                Map<String, String> expectedHeaders = aCase.getResponse().getHeaders();
                Map<String, String> actualHeaders = response.getHeaders();
                for (String expectedHeader : expectedHeaders.keySet()) {
                    Assertions.assertEquals(expectedHeaders.get(expectedHeader), actualHeaders.get(expectedHeader));
                }
            }

            if (aCase.getResponse().getBody() != null) {
                Assertions.assertEquals(aCase.getResponse().getBody(), response.getBody());
            }
        }
    }

    public Response runCase(Request request) throws Exception {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON));
        headers.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br"));
        headers.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));

        if (request.getHeaders() != null) {
            for (String key : request.getHeaders().keySet()) {
                headers.add(new BasicHeader(key, request.getHeaders().get(key)));
            }
        }

        BasicCredentialsProvider credsProvider = null;

        if (request.getBasic_auth_username() != null) {
            credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope(config.getSshHost(), 80),
                    new UsernamePasswordCredentials(request.getBasic_auth_username(), request.getBasic_auth_password().toCharArray()));
        }

        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustAllStrategy())
                .build();
        HostnameVerifier hostnameVerifier = new NoopHostnameVerifier();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslcontext, hostnameVerifier);

        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(socketFactory)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .evictExpiredConnections()
                .setDefaultHeaders(headers)
                .setDefaultCredentialsProvider(credsProvider)
                .build();

        String scheme = request.getScheme() == null ? "http" : request.getScheme();
        String port = request.getPort() == null ? "80" : request.getPort();
        String method = request.getMethod() == null ? "GET" : request.getMethod();
        String uri = request.getUri() == null ? "/" : request.getUri();
        if (!uri.startsWith("/")) {
            uri = "/" + uri;
        }

        String url = String.format("%s://%s:%s%s", scheme, config.getSshHost(), port, uri);

        CloseableHttpResponse response = null;

        if ("GET".equals(method)) {
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
        } else if ("POST".equals(method)) {
            HttpPost httpPost = new HttpPost(url);
            response = httpClient.execute(httpPost);
        } else if ("PUT".equals(method)) {
            HttpPut httpPut = new HttpPut(url);
            response = httpClient.execute(httpPut);
        }

        if (response != null) {
            int code = response.getCode();
            Map<String, String> responseHeaders = new HashMap<>();
            for (Header header : response.getHeaders()) {
                responseHeaders.put(header.getName(), header.getValue());
            }
            return new Response(String.valueOf(code), responseHeaders, EntityUtils.toString(response.getEntity()));
        }

        return null;
    }

    private void executePreAction(Scenario scenario) throws IOException {
        if (scenario.getPre_action() != null) {
            if (scenario.getPre_action() == Action.RESTART_NGINX) {
                String cmd = "systemctl restart nginx.service";
                executeSshCommand(cmd);
            }
        }
    }

    private void executePostAction(Scenario scenario) {
        if (scenario.getPost_action() != null) {
            if (scenario.getPost_action() == Action.CLEAR_ETCD_CONFIG) {
                clearGatewayConfig();
            }
        }
    }

    private void executeSshCommand(String cmd) throws IOException {
        SshHelper.SshConnection conn = new SshHelper.SshConnection(config.getSshHost(), config.getSshUser(), config.getSshPasswd());
        SshHelper.SshResponse response = runCommand(conn, cmd, 15);
        if (response.getReturnCode() != 0) {
            throw new RuntimeException("Failed to execute ssh:" + cmd);
        }
    }

    private void writeGatewayConfig(String scenarioPath) throws IOException {
        List<File> configs = CaseHelper.getCaseDataFiles(scenarioPath + "/configs");
        if (CollectionUtils.isEmpty(configs)) {
            return;
        }

        for (File config : configs) {
            FileReader reader = new FileReader(config);

            if (config.getName().startsWith("gateway_")) {
                Gateway gateway = gson.fromJson(reader, Gateway.class);
                etcdService.put(gateway.key(), gson.toJson(gateway));
            } else if (config.getName().startsWith("server_")) {
                Server server = gson.fromJson(reader, Server.class);
                etcdService.put(server.key(), gson.toJson(server));
            } else if (config.getName().startsWith("upstream_")) {
                Upstream upstream = gson.fromJson(reader, Upstream.class);
                etcdService.put(upstream.key(), gson.toJson(upstream));
            } else if (config.getName().startsWith("group_")) {
                LocationGroup group = gson.fromJson(reader, LocationGroup.class);
                etcdService.put(group.key(), gson.toJson(group));
            } else if (config.getName().startsWith("location_")) {
                Location location = gson.fromJson(reader, Location.class);
                etcdService.put(location.key(), gson.toJson(location));
            }
        }

        executeSshCommand("confd -onetime -backend etcdv3 -node " + config.getEtcdEndpoints());
    }

    private void clearGatewayConfig() {
        Map<String, String> map = etcdService.get("/gateways", true);
        for (String key : map.keySet()) {
            etcdService.delete(key);
        }
    }

    private Scenario parseScenario(String scenarioPath) throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(scenarioPath + "/scenario.json");
        assert url != null;
        return gson.fromJson(new FileReader(url.getFile()), Scenario.class);
    }
}
