package com.wolf.cloud.feign.client.impl;

import com.wolf.cloud.feign.client.HiFeignClient;
import org.springframework.stereotype.Component;

/**
 * Created by wolf on 17/9/25.
 */
@Component
public class HiFeignClientHystrixFallback implements HiFeignClient {

    @Override
    public String sayHiFromFeignClient(String name) {
        return "say hi from feign client fail back : " + name;
    }

}
