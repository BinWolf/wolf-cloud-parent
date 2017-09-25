package com.wolf.cloud.feign.client;

import com.wolf.cloud.feign.conf.FeignLogConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wolf on 17/9/23.
 */
@FeignClient(name = "wolf-cloud-user", configuration = FeignLogConfiguration.class)
public interface HiFeignClient {

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    String sayHiFromFeignClient(@PathVariable("name") String name);
}
