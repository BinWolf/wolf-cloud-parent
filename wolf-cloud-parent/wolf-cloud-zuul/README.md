# wolf-cloud-zuul

## zuul路由器
- 创建maven项目，在pom.xml导入

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zuul</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

- 在application.yml中配置
```
server:
  port: 8766
spring:
  application:
    name: wolf-cloud-zuul
eureka:
  client:
    servicerl:
     default-zone: http://localhost:8761/eureka/
zuul:
  routes:
    feign:
      path: /feign/**
      serviceId: wolf-cloud-feign-8765
    ribbon:
      path: /ribbon/**
      serviceId: wolf-cloud-ribbon-8764
```

> 请求uri中包含feign的转发到spring.application.name= wolf-cloud-feign-8765
> 请求uri中包含ribbon的转发到spring.application.name= wolf-cloud-ribbon-8764

- 在启动类中加上注解
```
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
```

- 启动项目
```
wolf-cloud-eureka
wolf-cloud-user
wolf-cloud-ribbon
wolf-cloud-feign
wolf-cloud-zuul
```
- 在浏览器中访问 

```
http://localhost:8766/feign/sayHi/wolf
返回： Hi, wolf from Feign; port = 8762

http://localhost:8766/ribbon/sayHi/wolf
返回：Hi, wolf from Ribbon; port = 8762
```
## zuul过滤器
