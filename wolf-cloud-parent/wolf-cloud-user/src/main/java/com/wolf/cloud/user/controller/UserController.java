package com.wolf.cloud.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wolf on 17/9/22.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/{name}")
    public String sayHi(@PathVariable("name") String name) {
        return "Hi, " + name + "; port = " + port;
    }
}
