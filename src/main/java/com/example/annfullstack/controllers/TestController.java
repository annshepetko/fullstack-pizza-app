package com.example.annfullstack.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/secured")
    public String securedString(){
        return "secured hello";
    }

}
