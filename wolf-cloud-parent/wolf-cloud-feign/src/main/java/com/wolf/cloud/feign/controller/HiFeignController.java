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
public class HiFeignController {

    @Autowired
    private HiFeignClient hiFeignClient;

    @GetMapping("/sayHi/{name}")
    public String sayHiFromFeign(@PathVariable("name") String name) {
        name = name + " from Feign";
        return hiFeignClient.sayHiFromFeignClient(name);
    }

    @GetMapping("/{name}")
    public String testBalance(@PathVariable("name") String name) {
        try {
            Thread.sleep(500);
            System.out.println("------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hi " + name;
    }
}
