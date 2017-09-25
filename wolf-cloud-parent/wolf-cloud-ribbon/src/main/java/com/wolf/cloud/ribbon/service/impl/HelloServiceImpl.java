package com.wolf.cloud.ribbon.service.impl;

import com.wolf.cloud.ribbon.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wolf on 17/9/22.
 * 用RestTemplate + ribbon 访问
 */
@Service
public class HelloServiceImpl implements IHelloService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String hiService(String name) {
        return restTemplate.getForObject("http://wolf-cloud-user/user/" + name, String.class);
    }
}
