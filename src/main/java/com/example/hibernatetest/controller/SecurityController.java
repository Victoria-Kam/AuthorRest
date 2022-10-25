package com.example.hibernatetest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/admin/get")
    public String getAdminInfo(){
        return "Hi, Admin";
    }

    @GetMapping("/author/get")
    public String getUserInfo(){
        return "Hi, User";
    }
}