package com.wolf.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wolf on 17/9/21.
 * @EnableDiscoveryClient 把服务注册到注册中心
 * @EnableHystrix 开启容错，断路器
 */

@SpringBootApplication
@EnableDiscoveryClient // 注册服务
@EnableHystrix // 断路器
public class RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced // 负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
