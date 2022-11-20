# etcd 安装配置简介

## 基于Centos的安装

```shell

sudo yum install etcd
sudo systemctl restart etcd

```
## 基于Centos的配置

vi /etc/etcd/etcd.conf

修改两行增加远程访问的endpoints地址：

```shell

ETCD_LISTEN_CLIENT_URLS="http://localhost:2379,http://192.168.51.27:2379"

ETCD_ADVERTISE_CLIENT_URLS="http://localhost:2379,http://192.168.51.27:2379"

```

## 指定etcd的API版本

默认为v2的接口版本，必须要强制指定v3版本：

```shell
export ETCDCTL_API=3
```



