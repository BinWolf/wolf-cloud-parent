package com.wolf.cloud.ribbon.controller;

import com.wolf.cloud.ribbon.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wolf on 17/9/22.
 */
@RestController
@RequestMapping("ribbon")
public class HelloController {

    @Autowired
    IHelloService helloService;

    @GetMapping("/sayHi/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloService.hiService(name);
    }
}
