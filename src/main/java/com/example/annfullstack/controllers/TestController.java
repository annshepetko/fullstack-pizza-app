package com.example.annfullstack.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(methods = {RequestMethod.POST}, origins = "http://localhost:3000", maxAge = 3600)
public class TestController {
    @GetMapping("/secured")
    public String securedString(){
        return "secured hello";
    }

}
