# 一个基于rabbitmq简单的日志记录注解。

## 运行

在 ``application.yaml`` 中配置如下即可使用 

```yaml

my-log:
  src: ./log/

spring:
  rabbitmq:
    host: 120.0.0.1 # 你的rabbitmq 服务器地址
    port: 5672 # rabbitmq端口
    username: jdfcc # 账号
    password: 1234 # 密码
```
