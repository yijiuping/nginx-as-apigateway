# 后端测试服务

为了更灵活、更接近真实的模拟后端服务，用Java实现了一个简易的微服务，具备常见微服务的主要特性，如：健康检查、Swagger接口文档、REST 接口等。

## 编译打包
`mvn clean install -DskipTests`

## 启动后端服务
java -jar backend-demo-0.0.1-SNAPSHOT.jar

## 验证服务状态
http://localhost:8080/actuator/health

`{"status":"UP"}`

http://localhost:8080/api/warehouse/inventory

`{"code":"200","message":"SUCCESS","data":[...]}`
