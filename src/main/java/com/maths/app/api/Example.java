package com.maths.app.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Example {
    @GetMapping("/hello")
    String execute(){
        return "hello world";
    };
}
