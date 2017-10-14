# wolf-cloud-user
 
- 在pom.xml导入
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>
```
- bootstrap.yml 加上
```
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
management:
  security:
    enabled: false
```
> management.security.enabled=false 允许刷新访问，这个未配置，/bus/refresh 会不被允许访问

- 在需要获取配置变量的类上加上注解 @RefreshScope, 更新git配置特殊处理
