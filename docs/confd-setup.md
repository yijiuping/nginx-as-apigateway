# Confd配置简要介绍

## 安装Confd

```shell

wget https://github.com/kelseyhightower/confd/releases/download/v0.16.0/confd-0.16.0-linux-amd64
mkdir -p /opt/confd/bin
mv confd-0.16.0-linux-amd64 /opt/confd/bin/confd
chmod +x /opt/confd/bin/confd
export PATH="$PATH:/opt/confd/bin"

```
## 创建Confd配置目录

```shell
mkdir /etc/confd
mkdir /etc/confd/conf.d
mkdir /etc/confd/templates

```

## 单次执行配置更新

```shell

confd -onetime -backend etcdv3 -node http://192.168.51.27:2379

```

注意：显示指定基于 etcdv3 的接口。

## 监听并持续执行配置更新

```shell

confd -backend etcdv3 -node http://192.168.51.27:2379

```
注意：显示指定基于 etcdv3 的接口。
