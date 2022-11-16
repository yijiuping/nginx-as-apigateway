package com.nginx.gateway.service;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>EtcdService</p>
 *
 * @author Jiuping Yi
 */
@Slf4j
@Service
public class EtcdService {

    @Value("${etcd.endpoints: http://127.0.0.1:2379}")
    private String endpoints;

    public Client getEtcdClient() {
        return Client.builder().endpoints(endpoints).build();
    }

    public void put(String key, String value) {
        try {
            KV kvClient = getEtcdClient().getKVClient();
            ByteSequence bsKey = ByteSequence.from(key.getBytes());
            ByteSequence bsValue = ByteSequence.from(value.getBytes());
            kvClient.put(bsKey, bsValue).get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void delete(String key) {
        try {
            KV kvClient = getEtcdClient().getKVClient();
            ByteSequence bsKey = ByteSequence.from(key.getBytes());

            kvClient.delete(bsKey).get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public String get(String key) {
        try {
            KV kvClient = getEtcdClient().getKVClient();
            ByteSequence bsKey = ByteSequence.from(key.getBytes());

            GetResponse response = kvClient.get(bsKey).get();
            if (response.getCount() > 0) {
                return response.getKvs().get(0).getValue().toString(StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public Map<String, String> get(String key, boolean prefix) {
        try {
            KV kvClient = getEtcdClient().getKVClient();
            ByteSequence bsKey = ByteSequence.from(key.getBytes());

            GetOption option = GetOption.newBuilder().isPrefix(prefix).build();
            GetResponse response = kvClient.get(bsKey, option).get();
            List<KeyValue> keyValues = response.getKvs();
            Map<String, String> map = new HashMap<>();
            for (KeyValue keyValue : keyValues) {
                map.put(keyValue.getKey().toString(StandardCharsets.UTF_8), keyValue.getValue().toString(StandardCharsets.UTF_8));
            }
            return map;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
