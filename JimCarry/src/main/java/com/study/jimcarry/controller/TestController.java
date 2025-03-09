package com.study.jimcarry.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/")
    public String index() {
        return "CI/CD Deploy Test - Active Profile: " + activeProfile +
                " | Server Port: " + serverPort +
                " | Zero-downtime deployment test";
    }
}
