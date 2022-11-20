# 基于Go Template和Confd的配置模板

[Confd](https://github.com/kelseyhightower/confd) 是一个自动化配置工具，实现了基于[Go Template](https://pkg.go.dev/text/template) 的配置模板渲染功能，
其同时支持监听与读取 [etcd](https://github.com/coreos/etcd )、Consul 等键值存储服务的配置内容，结合配置内容与配置模板可生成最终的配置内容。利用此功能架构，我们可实现NGINX配置文件的

## 开发配置模板

配置模板的开发基于[配置设计](../config-design/README.md)与[模型设计](../config-model/README.md) ，同时支持NGINX OSS和NGINX Plus两个版本的NGINX底座。

Confd的配置文件生成需要两个文件，一个是配置基本信息，如：

/etc/confd/conf.d/api_backend.toml

```

[template]
src = "api_backends.tmpl"
dest = "/etc/nginx/api_backends.conf"
owner = "nginx"
mode = "0644"
keys = [
  "/gateways/6369a89cf67ffcb560daa2a8",
]
check_cmd = "nginx -t -c {{.src}}"
reload_cmd = "nginx -s reload"

```

另一个为配置模板，如：

/etc/confd/templates/api_backend.tmpl

```gotemplate

# Backend configuration
#
{{ $gateway_id := getenv "GATEWAY_IP" "6369a89cf67ffcb560daa2a8" -}}
{{- $servers := printf "/gateways/%s/http/servers" $gateway_id -}}
{{- range $index, $server_id := ls $servers -}}
    {{- $server_key := printf "/gateways/%s/http/servers/%s" $gateway_id $server_id -}}
    {{- $upstreams := printf "/gateways/%s/http/servers/%s/upstreams" $gateway_id $server_id -}}
    {{- $server := json (getv $server_key) -}}
    {{- range $upstream_index, $upstream_id := ls $upstreams -}}
        {{- $upstream_key := printf "/gateways/%s/http/servers/%s/upstreams/%s" $gateway_id $server_id $upstream_id -}}
        {{- $upstream := json (getv $upstream_key) -}}
        {{ $members := $upstream.members }}
upstream {{$upstream.name}} {
    zone {{$upstream.name}} 64k;
        {{- if $upstream.policy.load_balance }}
            {{ $algorithm:= (or $upstream.policy.load_balance.algorithm "ROUND_ROBIN") }}
            {{- if eq $algorithm "RANDOM" }}
    random;
            {{- else if eq $algorithm "IP_HASH"}}
    ip_hash;
            {{- else if eq $algorithm "HASH" }}
    hash {{$upstream.policy.load_balance.hash_key}};
            {{- else if eq $algorithm "CONSISTENT_HASH" }}
    hash {{$upstream.policy.load_balance.hash_key}} consistent;
            {{- else if eq $algorithm "LEAST_CONN" }}
    least_conn;
            {{- else if eq $algorithm "LEAST_TIME" }}
    least_time header;
            {{- end -}}
        {{ end }}

        {{- range $member_index, $member := $members }}
    server {{$member.address}} weight={{or $member.weight "1"}}  max_conns={{or $upstream.policy.upstream_conn.max_conns "0"}}  max_fails={{or $upstream.policy.upstream_conn.max_fails "3"}}  fail_timeout={{or $upstream.policy.upstream_conn.fail_timeout "5"}} ;
        {{- end }}

    keepalive {{or $upstream.policy.upstream_conn.keepalive "32"}};
    keepalive_timeout {{or $upstream.policy.upstream_conn.keepalive_timeout "60s"}};
    keepalive_requests {{or $upstream.policy.upstream_conn.keepalive_requests "1000"}};
    keepalive_time {{or $upstream.policy.upstream_conn.keepalive_time "1h"}};
}
    {{ end }}
{{- end }}


```
