package com.study.jimcarry.controller;

import org.checkerframework.checker.formatter.qual.ReturnsFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }
}
