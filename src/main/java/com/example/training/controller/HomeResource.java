package com.example.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String sayHello(){
        return "Welcome to Tomcat Server :) ";
    }

}
