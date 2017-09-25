package com.wolf.cloud.feign.controller;

import com.wolf.cloud.feign.client.HiFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wolf on 17/9/23.
 */
@RestController
@RequestMapping("feign")
public class HiFeignController {

    @Autowired
    private HiFeignClient hiFeignClient;

    @GetMapping("/sayHi/{name}")
    public String sayHiFromFeign(@PathVariable("name") String name) {
        return hiFeignClient.sayHiFromFeignClient(name);
    }
}
