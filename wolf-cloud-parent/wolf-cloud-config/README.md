# wolf-cloud-config 服务器

- 创建maven项目，在pom.xml导入

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>

```

- 在启动类中加上注解

```
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
```

- 在application.yml中配置
```
server:
  port: 8767
spring:
  application:
    name: wolf-cloud-config
  cloud:
    config:
      server:
        git:
          uri: https://github.com/BinWolf/wolf-cloud-parent.git
          search-paths: wolf-cloud-parent/wolf*

eureka:
  client:
    servicerl:
     default-zone: http://localhost:8761/eureka/
```
